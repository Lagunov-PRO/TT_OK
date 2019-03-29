package ru.open.kzn.autotests;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPageLogicalSlider {




    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests();

    @Before
    public void MainPageOpen() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void MainPageSliderHeatStart() {

        DateTimeFormatter month = DateTimeFormatter.ofPattern( "MM" );
        LocalDateTime todayDate = LocalDateTime.now();
        String todayMonth = todayDate.format(month);
        int todayMonthDigit = Integer.parseInt(todayMonth);
        List<Integer> monthsWithHeat = Arrays.asList(10, 11, 12, 1, 2, 3);
        if (monthsWithHeat.contains(todayMonthDigit)) {
            $$(By.className("slide__text")).find(text("Старт отопительного сезона!")).shouldBe(disappear);
        }
        else {
            $$(By.className("slide__text")).find(text("Старт отопительного сезона!")).shouldBe(visible);
        }


    }

}
