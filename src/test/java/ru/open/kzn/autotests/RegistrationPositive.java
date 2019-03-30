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

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

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
//
//        $("#auth").find(byAttribute("data-ui","registration")).click();
//
        clearBrowserCache();
        open("https://mail.google.com/mail/h/1pq68r75kzvdr/?v%3Dlui");
        $("#identifierId").setValue(registeredEmail).pressEnter();
        $("#password input").setValue(registeredEmailPassword).pressEnter();
        $$(By.className("ts")).get(1).click();
        $(By.linkText(timestampEmail));
        String emailSource = source();
        String[] array1 = emailSource.split("( </span>)");
        String emailSourcePasswordPart = array1[1];
        int length = emailSourcePasswordPart.length();
        String password = emailSourcePasswordPart.substring(length - 7, length);
        System.out.println(password);





//        $(By.name("email")).setValue(timestampEmail);
//        String registeredUserLogin = "open.kzn.registered@gmail.com";
//        String registeredUserPassword = "q6108r";
//        $(By.name("password")).setValue(rot13(registeredUserPassword)).pressEnter();
//        Assert.assertEquals("https://open.kzn.ru/cabinet/", url());
//        $(("#authInfo")).find((".username")).shouldHave(text(registeredUserLogin));
//
//        $(("#onesignal-popover-allow-button")).click();
//        $(("#authInfo")).find((".btnExit")).click();

    }



}
