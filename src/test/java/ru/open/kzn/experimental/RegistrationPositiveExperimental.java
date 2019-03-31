package ru.open.kzn.experimental;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

public class RegistrationPositiveExperimental {


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

        $(".searchPageLink").click();


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
//                $(byAttribute("value", "Delete")).click();

                }

            for (int i = 0; i < emailLinkTextList.size(); i++) {
                if (emailLinkTextList.get(i).contains("21.37.30.03.2019@lagunov.pro")) {

                    String newUserPassword = emailLinkTextList.get(i - 2);
                }
                }

            $(".searchPageLink").click();
            messagesInbox--;

            }

        }

    }
