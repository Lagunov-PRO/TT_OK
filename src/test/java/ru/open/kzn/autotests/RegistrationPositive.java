package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assert.assertEquals;

public class RegistrationPositive {

    private static String registeredEmail = "open.kzn.registered@gmail.com";
    private static String rawPW = "MjM2LTQtMTIz";


    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();


    @Test
    public void RegistrationPositiveNewUser() {
        // login.reg.0000

        registerNewUser();


        open("https://mail.google.com/mail/h/1pq68r75kzvdr/?v%3Dlui");  // По этой ссылке откроется упрощённый интерфейс
        $("#identifierId").setValue(registeredEmail).pressEnter();
        $("#password input").setValue(pwDecode(rawPW)).pressEnter();

        // Пока нет ни одного письма, обновляем стариницу.
        // FIXME: Опасный цикл, что если там осталось другое письмо, а новое ещё не пришло?
        while (getElements(By.className("ts")).size() == 0) {

            $(".searchPageLink").click();

        }

        $$(By.className("ts")).get(0).click(); // Кликаем на первое письмо
//        $(By.linkText("?&cs=wh&v=b&to=" + timestampEmail));  //  FIXME: Проверка того, что письмо для правильного email открылось

        String emailSource = source();
        $(byAttribute("value", "Delete")).click();
        String[] array1 = emailSource.split("( </span>)");
        String emailSourcePasswordPart = array1[1];
        int length = emailSourcePasswordPart.length();
        String timestampEmailPassword = emailSourcePasswordPart.substring(length - 7, length);
        System.out.println(timestampEmailPassword);

        open("https://open.kzn.ru/");
        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(By.name("username")).val(timestampEmail).pressTab();
        $(By.name("password")).val(timestampEmailPassword).pressEnter();
        assertEquals("https://open.kzn.ru/cabinet/", url());
        open("https://open.kzn.ru/cabinet/myprofile");
        $(("#authInfo")).find((".username")).shouldHave(text(timestampEmail));
        $(".onesignal-bell-launcher-button").waitUntil(visible, 10000);
        $(("#onesignal-popover-allow-button")).waitUntil(visible, 5000);
        $(("#onesignal-popover-allow-button")).click();
        $(("#onesignal-popover-allow-button")).waitUntil(disappears, 5000);
        $("#deleteProfile").click();
        $(byAttribute("data-ui","btnSuccess")).click();
        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).waitUntil(visible, 10000);;
        $(By.id("js_nofify")).shouldHave(text("Пользователь удалён"));



    }

    private static void registerNewUser() {
        open("https://open.kzn.ru/");
        $("#auth").find(byAttribute("data-ui","registration")).click();
        $(byAttribute("data-ui", "email")).setValue(generateTimestampEmail()).pressEnter();

    }

    private static String generateTimestampEmail() {
        DateTimeFormatter timestamp = DateTimeFormatter.ofPattern( "HH.mm.dd.MM.yyyy" );
        LocalDateTime currentDayTime = LocalDateTime.now();
        String timestampEmail = currentDayTime.format(timestamp);
        timestampEmail += "@lagunov.pro";
        return timestampEmail;
    }

    private static String pwDecode(String input) {

        byte[] pwBytes = Base64.getDecoder().decode((input).getBytes());

        return new String(pwBytes);
    }


}
