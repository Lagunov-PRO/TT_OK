package ru.open.kzn.autotests;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class FrontPageViewTest {

    @Test
    public void AllLinksWork() {

        open("https://open.kzn.ru");
    }
}
