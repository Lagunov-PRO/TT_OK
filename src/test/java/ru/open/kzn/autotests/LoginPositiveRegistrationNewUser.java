package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.*;

public class LoginPositiveRegistrationNewUser {


    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void SendOrderEmptyPersonalAccount() {

        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(By.name("username")).setValue("open.kzn.registered@gmail.com");
        $(By.name("password")).setValue("236-4-123").pressEnter();
        $(id("js_nofify")).shouldHave(text("Ошибка входа в личный кабинет"));

    }

}
