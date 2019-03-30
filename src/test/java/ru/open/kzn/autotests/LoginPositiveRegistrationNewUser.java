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
        String registeredUserPassword = "q6108r";
        $(By.name("password")).setValue(rot13(registeredUserPassword)).pressEnter();
        $(id("js_nofify")).shouldHave(text("Ошибка входа в личный кабинет"));

    }

    private static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }
}