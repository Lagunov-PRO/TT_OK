package ru.open.kzn.autotests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assert.assertEquals;

public class RegistrationPositive {


    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void MainOpen() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void RegistrationPositiveNewUser() {

        String registeredEmail = "open.kzn.registered@gmail.com";
        String registeredEmailPassword = "236-4-123";
        DateTimeFormatter timestamp = DateTimeFormatter.ofPattern( "HH.mm.dd.MM.yyyy" );
        LocalDateTime currentDayTime = LocalDateTime.now();
        String timestampEmail = currentDayTime.format(timestamp);
        timestampEmail += "@lagunov.pro";

        $("#auth").find(byAttribute("data-ui","registration")).click();
        $(byAttribute("data-ui", "email")).setValue(timestampEmail).pressEnter();

        open("https://mail.google.com/mail/h/1pq68r75kzvdr/?v%3Dlui");
        $("#identifierId").setValue(registeredEmail).pressEnter();
        $("#password input").setValue(registeredEmailPassword).pressEnter();

        while (getElements(By.className("ts")).size() != 1) {

            $(".searchPageLink").click();

        }

        $$(By.className("ts")).get(0).click();
//        $(By.linkText(timestampEmail));
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
        $(".onesignal-bell-launcher-button").waitUntil(visible, 1000);;
        $(("#onesignal-popover-allow-button")).waitUntil(visible, 1000);
        $(("#onesignal-popover-allow-button")).click();
        $(("#onesignal-popover-allow-button")).waitUntil(disappears, 1000);
        $("#deleteProfile").click();
        $(byAttribute("data-ui","btnSuccess")).click();
//        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).waitUntil(visible, 1000);;
//        $(By.id("js_nofify")).shouldHave(text("Пользователь удалён"));
//        $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("close")).click();


    }



}
