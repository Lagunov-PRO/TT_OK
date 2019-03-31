package ru.open.kzn.autotests;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPositive {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void openMain() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void loginPositiveRegisteredUser() {

        $("#auth").find(byAttribute("data-ui","auth")).click();
        String registeredUserLogin = "open.kzn.registered@gmail.com";
        String registeredUserPassword = "q6108r";
        $(By.name("username")).setValue(registeredUserLogin);
        $(By.name("password")).setValue(rot13(registeredUserPassword)).pressEnter(); // Восстанавливаем пароль
        Assert.assertEquals("https://open.kzn.ru/cabinet/", url());  // Проверка, что открыта личный кабинет
        $(("#authInfo")).find((".username")).shouldHave(text(registeredUserLogin));  // Проверка, что надпись в кабинете соответствует логину

        $(("#onesignal-popover-allow-button")).click();  // Клик на запрос Push-уведомлений, иначе Выход не нажать
        $(("#authInfo")).find((".btnExit")).click(); // Выходим

    }

    // Преобразование пароля, чтобы хотя бы плеинтекстом в гитхабе не лежал
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
