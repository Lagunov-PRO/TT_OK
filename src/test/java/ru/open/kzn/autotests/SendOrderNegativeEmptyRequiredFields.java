package ru.open.kzn.autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendOrderNegativeEmptyRequiredFields {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void SendOrderEmptyPersonalAccount() {

        $(By.name("personalaccount")).setValue("").pressEnter();
        $(byText("Ошибка")).shouldBe(visible);

    }

    @Test
    public void SendOrderEmptyPhone() {

        $(By.name("phone")).setValue("").pressEnter();
        $(byText("Ошибка")).shouldBe(visible);

    }

}
