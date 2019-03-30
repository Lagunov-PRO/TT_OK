package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPositiveThirdPartyServices {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void MainOpen() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void LoginPositiveThirdPartyServicesGosuslugi() {

        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(byAttribute("data-ui","esia")).click();
        open("https://esia.gosuslugi.ru/idp/rlogin?cc=bp");

        $("#mobileOrEmail").setValue("test").pressEnter();
        String currentURL = url();
        System.out.println(currentURL);
        System.out.println(currentURL.contains("https://esia.gosuslugi.ru/"));


    }


}
