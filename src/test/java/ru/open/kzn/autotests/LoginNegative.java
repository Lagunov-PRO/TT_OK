package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.openqa.selenium.By.id;

public class LoginNegative {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void openMain() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void loginNegativeRegisteredUserWrongPassword() {
    // login.ent.0002
        String registeredUserLogin = "open.kzn.unregistered@gmail.com";
        String registeredUserPassword = "q6108r";
        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(By.name("username")).setValue(registeredUserLogin);
        $(By.name("password")).setValue(registeredUserPassword).pressEnter();

        $(("#js_nofify")).shouldHave(text("Ошибка входа в личный кабинет"));

    }

    @Test
    public void loginNegativeUnregisteredUser() {
        //login.ent.0003
        String unregisteredUserLogin = "open.kzn.unregistered@gmail.com";
        String unregisteredUserPassword = "ABC123";
        $("#auth").find(byAttribute("data-ui","auth")).click();
        $(By.name("username")).setValue(unregisteredUserLogin);
        $(By.name("password")).setValue(unregisteredUserPassword).pressEnter();

        $(("#js_nofify")).shouldHave(text("Ошибка входа в личный кабинет"));

    }


}
