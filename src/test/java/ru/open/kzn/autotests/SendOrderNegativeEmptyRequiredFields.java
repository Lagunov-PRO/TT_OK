package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendOrderNegativeEmptyRequiredFields {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void openSendOrder() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void sendOrderEmptyPersonalAccount() {

        $(By.name("personalaccount")).setValue("").pressEnter();
        $(By.id("js_nofify")).shouldHave(text("Ошибка! Вы не заполнили \"Лицевой счет\""));

    }

    @Test
    public void sendOrderEmptyPhone() {

        $(By.name("phone")).setValue("").pressEnter();
        $(By.id("js_nofify")).shouldHave(text("Ошибка! Вы не заполнили \"Номер телефона\""));

    }

    @Test
    public void sendOrderEmptyStreet() {

        $(By.name("street")).setValue("").pressEnter();
        $(By.id("js_nofify")).shouldHave(text("Ошибка! Вы не заполнили \"Улица\""));

    }

    @Test
    public void sendOrderEmptyFlat() {

        $(By.name("flatnumber")).setValue("").pressEnter();
        $(By.id("js_nofify")).shouldHave(text("Ошибка! Вы не заполнили \"Квартира\"\n"));

    }

    @Test
    public void sendOrderEmptyHouse() {

        $(By.name("housenumber")).setValue("").pressEnter();
        $(By.id("js_nofify")).shouldHave(text("Ошибка! Вы не заполнили \"Дом\""));

    }



}
