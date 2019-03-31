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
    public void openMain() {
        open("https://open.kzn.ru/");
    }

    @Test
    public void checkSliderHeatActuality() {

        // Логическая проверка, что после окончания отопительного сезона, баннер про отопление нужн бы убрать
        DateTimeFormatter month = DateTimeFormatter.ofPattern( "MM" ); // Паттерн для отрезания от даты только месяца
        LocalDateTime todayDate = LocalDateTime.now(); // Получаем местное время
        String todayMonth = todayDate.format(month); // Применяем паттерн
        int todayMonthDigit = Integer.parseInt(todayMonth); // Преобразум месяц из строки в число
        List<Integer> monthsWithHeat = Arrays.asList(10, 11, 12, 1, 2, 3); // Примерный список отопительных месяцев
        if (monthsWithHeat.contains(todayMonthDigit)) {  // Если сегодня не отопительный сезон, то сообщения не должно быть
            $$(By.className("slide__text")).find(text("Старт отопительного сезона!")).shouldBe(disappear);
        }
        else {
            $$(By.className("slide__text")).find(text("Старт отопительного сезона!")).shouldBe(visible);
        }


    }

}
