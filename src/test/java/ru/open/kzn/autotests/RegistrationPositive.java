package ru.open.kzn.autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
    public void registrationPositiveNewUser() {
        // login.reg.0000

        String timestampEmail = generateTimestampEmail();
        registerNewUser(timestampEmail);
        String newUserPassword = getPasswordFromEmail(timestampEmail);
        loginNewUser(timestampEmail, newUserPassword);
        deleteNewUser(timestampEmail);

    }

    private static void registerNewUser(String timestampEmail) {
        open("https://open.kzn.ru/");
        $("#auth").find(byAttribute("data-ui","registration")).click();
        $(byAttribute("data-ui", "email")).setValue(timestampEmail).pressEnter();

    }

    private static String generateTimestampEmail() {
        DateTimeFormatter timestamp = DateTimeFormatter.ofPattern( "HH.mm.ss_dd-MM-yyyy" );
        LocalDateTime currentDayTime = LocalDateTime.now();
        String timestampEmail = currentDayTime.format(timestamp);
        timestampEmail += "@lagunov.pro";
        return timestampEmail;
    }

    private static String getPasswordFromEmail(String timestampEmail) {

        String newUserPassword = "";

        open("https://mail.google.com/mail/h/1pq68r75kzvdr/?v%3Dlui");  // По этой ссылке откроется упрощённый интерфейс
        if ($(By.tagName("title")).getText().equals("Do you really want to use HTML Gmail?")){

            $("#maia-main").find(By.className("maia-button maia-button-secondary")).click();
        }
        $("#identifierId").setValue(registeredEmail).pressEnter();
        $("#password input").setValue(pwDecode(rawPW)).pressEnter();

        $(".searchPageLink").click();

        // Если ящик пустой, ждём 5 секунд
        if ($$(By.className("ts")).size() == 0) {

            $(".searchPageLink").waitUntil(visible, 5000).click();

        }


        int messagesInbox = $$(By.className("ts")).size(); // Определяме кол-во писем
        while (messagesInbox != 0) {

            $$(By.className("ts")).get(messagesInbox - 1).click(); // Кликаем на нижнее письмо
            int emailLinkNumber = $$(By.tagName("span")).size();  // Определяем кол-во элементов с тегом span
            List<String> emailLinkTextList = new ArrayList<>();  // Создаём список

            while (emailLinkNumber != 0) {

                SelenideElement emailLinkText = $$(By.tagName("span")).get(emailLinkNumber - 1);  // Получаем текст первого элемента
                String emailLinkTextString = emailLinkText.toString();
                emailLinkTextString = emailLinkTextString.replace("<span>", "").replace("</span>", "");
                emailLinkTextList.add(emailLinkTextString);
                emailLinkNumber--;

            }

            for (int i = 0; i < emailLinkTextList.size(); i++) {

                if (emailLinkTextList.get(i).contains(timestampEmail)) {

                    newUserPassword = emailLinkTextList.get(i - 2);
                    $(byAttribute("value", "Delete")).click();  // Удаляем письмо c найдённым паролем

                }
            }

            $(".searchPageLink").click();  // Возврат к списку писем

            messagesInbox--;  // Уменьшаем индекс письма для перехода к следующему

        }
        return newUserPassword;
    }


    private static void loginNewUser(String timestampEmail, String timestampEmailPassword) {

        open("https://open.kzn.ru/");
        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(By.name("username")).val(timestampEmail).pressTab();
        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).waitUntil(visible, 1000).has(text("Ошибка входа в личный кабинет"));
        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("close")).click();
        $(By.name("password")).val(timestampEmailPassword).pressEnter();
        $(("#authInfo")).find((".username")).shouldHave(text(timestampEmail));

    }

    private static void deleteNewUser(String timestampEmail) {

        open("https://open.kzn.ru/cabinet/myprofile");
        $(("#authInfo")).find((".username")).shouldHave(text(timestampEmail));
        $(".onesignal-bell-launcher-button").waitUntil(visible, 10000);
        $(("#onesignal-popover-allow-button")).waitUntil(visible, 5000);
        $(("#onesignal-popover-allow-button")).click();
        $(("#onesignal-popover-allow-button")).waitUntil(disappears, 5000);
        $("#deleteProfile").click();
        $(byAttribute("data-ui","btnSuccess")).click();
        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).waitUntil(visible, 1000).has(text("Профиль удален!"));
    }

    private static String pwDecode(String input) {

        byte[] pwBytes = Base64.getDecoder().decode((input).getBytes());
        return new String(pwBytes);
    }
}
