package ru.open.kzn.autotests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SendOrderBruteForsePersonalAccount {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void SendOrderEmptyPersonalAccount() {

        int i = 1;
        String error = "Ошибка";
        Boolean flag = Boolean.TRUE;

        while (flag == Boolean.TRUE) {
            String personalAccountGuess = Integer.toString(i);
            $(By.name("personalaccount")).setValue(personalAccountGuess).pressEnter();
            SelenideElement personalAccountError = $(By.id("js_nofify")).shouldHave(text("Ошибка"));

            String result = personalAccountError.toString().substring(20, 27);
            if (result.equals(error)) { flag = Boolean.FALSE; }
            i++;
            System.out.println(result);
        }

    }


}
