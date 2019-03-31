package ru.open.kzn.autotests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

public class RegistrationPositive2 {


    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Test
    public void RegistrationPositiveNewUser() {
        // login.reg.0000
        String registeredEmail = "open.kzn.registered@gmail.com";
        String registeredEmailPassword = "236-4-123";
//        DateTimeFormatter timestamp = DateTimeFormatter.ofPattern( "HH.mm.dd.MM.yyyy" );
//        LocalDateTime currentDayTime = LocalDateTime.now();
//        String timestampEmail = currentDayTime.format(timestamp);
        String timestampEmail = "21.05.30.03.2019@lagunov.pro";
//
//        $("#auth").find(byAttribute("data-ui","registration")).click();
//        $(byAttribute("data-ui", "email")).setValue(timestampEmail).pressEnter();

        open("https://mail.google.com/mail/h/1pq68r75kzvdr/?v%3Dlui");  // По этой ссылке откроется упрощённый интерфейс
        $("#identifierId").setValue(registeredEmail).pressEnter();
        $("#password input").setValue(registeredEmailPassword).pressEnter();

        // Пока нет ни одного письма, обновляем стариницу.
        // FIXME: Опасный цикл, что если там осталось другое письмо, а новое ещё не пришло?
        $(".searchPageLink").click();

//        System.out.println($$(By.className("ts")));
//        System.out.println($$(By.className("ts")).size());
//        System.out.println($$(By.className("ts")).get());
        int messagesInbox = $$(By.className("ts")).size(); // Кликаем на первое письмо
        while (messagesInbox != 0) {


            $$(By.className("ts")).get(messagesInbox - 1).click(); // Кликаем на первое письмо

            int emailLinkNumber = $$(By.tagName("span")).size();
            while (emailLinkNumber != 0) {
                  //  FIXME: Проверка того, что письмо для правильного email открылось
                SelenideElement emailLinkText = $$(By.tagName("span")).get(emailLinkNumber - 1);  //
                String emailLinkTextString = emailLinkText.toString();
                System.out.println(emailLinkTextString);
                emailLinkNumber--;
//                $(byAttribute("value", "Delete")).click();
            }
            $(".searchPageLink").click();
            messagesInbox--;
        }
//        String emailSource = source();
//        String[] array1 = emailSource.split("( </span>)");
//        String emailSourcePasswordPart = array1[1];
//        int length = emailSourcePasswordPart.length();
//        String timestampEmailPassword = emailSourcePasswordPart.substring(length - 7, length);
//        System.out.println(timestampEmailPassword);
//
//        open("https://open.kzn.ru/");
//        $("#auth").find(byAttribute("data-ui","auth")).click();
//        $(By.name("username")).val(timestampEmail).pressTab();
//        $(By.name("password")).val(timestampEmailPassword).pressEnter();
//        assertEquals("https://open.kzn.ru/cabinet/", url());
//        open("https://open.kzn.ru/cabinet/myprofile");
//        $(("#authInfo")).find((".username")).shouldHave(text(timestampEmail));
//        $(".onesignal-bell-launcher-button").waitUntil(visible, 1000);
//        $(("#onesignal-popover-allow-button")).waitUntil(visible, 1000);
//        $(("#onesignal-popover-allow-button")).click();
//        $(("#onesignal-popover-allow-button")).waitUntil(disappears, 1000);
//        $("#deleteProfile").click();
//        $(byAttribute("data-ui","btnSuccess")).click();
//        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).waitUntil(visible, 1000);;
//        $(By.id("js_nofify")).shouldHave(text("Пользователь удалён"));
//        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("close")).click();


    }



}
