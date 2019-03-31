package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendOrderPositiveValidFields {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void sendOrderValidPersonalAccount() {

        String sendOrderValidPersonalAccount = "8107777775";
        String msgOrderPersonalAccountUnregistered = "Ваш номер лицевого счета не зарегистрирован в базе данных.";


        $(By.name("personalaccount")).setValue(sendOrderValidPersonalAccount).pressEnter();
        $(By.id("js_nofify")).shouldNotHave(text(msgOrderPersonalAccountUnregistered));

    }




}
