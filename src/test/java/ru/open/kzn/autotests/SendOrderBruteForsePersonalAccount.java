package ru.open.kzn.autotests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SendOrderBruteForsePersonalAccount {

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void SendOrderEmptyPersonalAccount() throws InterruptedException {

        int i = 880;
        String error = "Ваш номер лицевого счета не зарегистрирован в базе данных";
        Boolean flag = Boolean.TRUE;

        while (flag == Boolean.TRUE) {
            String personalAccountGuess = Integer.toString(i);
            $(By.name("personalaccount")).setValue(personalAccountGuess).pressTab();
            $(By.name("lastname")).pressEnter();
            SelenideElement personalAccountError = $(By.id("js_nofify")).shouldHave(text("Ошибка"));
            String result = personalAccountError.toString();
            if (result.contains(error)) {
                System.out.println(i);
                $(By.name("personalaccount")).clear();

                if ((i % 50) == 0) SendOrderOpen();
                i++;
            }
            else {
                System.out.println("STOP");

                flag = Boolean.FALSE; }
        }

    }


}
