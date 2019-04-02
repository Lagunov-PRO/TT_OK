package ru.open.kzn.experimental;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class DeleteUsersBatch {


    private static String registeredEmail = "open.kzn.registered@gmail.com";
    private static String rawPW = "MjM2LTQtMTIz";


    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();


    @Test
    public void deleteAllTimestampUsers() {
        // login.reg.0000

//        String timestampEmail = generateTimestampEmail();
//        registerNewUser(timestampEmail);
//        String newUserPassword = getPasswordFromEmail(timestampEmail);
//        loginNewUser(timestampEmail, newUserPassword);
//        deleteNewUser(timestampEmail);


        Map newUsersLoginsPasswords = getAllNewUsersLoginsPasswords();

        newUsersLoginsPasswords.forEach((k, v) -> System.out.println(k.toString() + v.toString()));

    }

    private static void registerNewUser(String timestampEmail) {
        open("https://open.kzn.ru/");
        $("#auth").find(byAttribute("data-ui","registration")).click();
        $(byAttribute("data-ui", "email")).setValue(timestampEmail).pressEnter();

    }

    private static Map getAllNewUsersLoginsPasswords() {

        Map<String, String> newUsersLoginsPasswords = new HashMap<>();


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

                if (emailLinkTextList.get(i).contains("Пароль")) {

                    newUsersLoginsPasswords.put(emailLinkTextList.get(i + 1), emailLinkTextList.get(i - 1));
//                    $(byAttribute("value", "Delete")).click();  // Удаляем письмо c найдённым паролем

                }
            }

            $(".searchPageLink").click();  // Возврат к списку писем

            messagesInbox--;  // Уменьшаем индекс письма для перехода к следующему

        }
//        for (Map.Entry<String,String> entry : newUsersLoginsPasswords.entrySet())
//            System.out.println(entry.getKey() + "  " + entry.getValue());

        return newUsersLoginsPasswords;
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
