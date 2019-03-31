package ru.open.kzn.experimental;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SendOrderPositiveProblemsListElements {

    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void SendOrderOpen() {
        open("https://open.kzn.ru/sendorder");
    }

    @Test
    public void ProblemListHasAllElements() {

        $$(By.xpath("//[@id='selectProblemsObject']//[@class='listOption']//*[@class='option']//*[text()]")).shouldHaveSize(26);
    }
}
