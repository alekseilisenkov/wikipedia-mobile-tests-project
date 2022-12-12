package com.alexlis.tests;

import com.alexlis.annotations.JiraIssue;
import com.alexlis.annotations.Layer;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

@Owner("Lisenkov")
@Story("wikipedia tests")
@Layer("mobile")
public class AndroidTests extends TestBase {

    @Test
    @Tag("mobile")
    @JiraIssue("AUTO-1451")
    @DisplayName("Поиск в приложении")
    @Feature("Поиск")
    void searchingWikipediaTest() {
        step("Загрузка приложения", () -> {
            textView.shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Нажатие кнопки назад. Пропуск начальных экранов.", () -> {
            back();
        });
        step("Нажатие на строку ввода", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        });
        step("Ввод текста в строку поиска", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });
        step("Проверка, поиска", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(text("Appium"));
        });
    }

    @Test
    @Tag("mobile")
    @JiraIssue("AUTO-1451")
    @DisplayName("Прохождение начального экрана")
    @Feature("Начальный экран")
    void getStartedWikipediaTest() {
        step("Проверка текста на первом экране приложения", () -> {
            textView
                    .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Клик кнопки продолжить и проверка экран", () -> {
            forwardButton.click();
            textView.shouldHave(text("New ways to explore"));
        });
        step("Нажатие кнопки продолжить и проверка третьего экрана", () -> {
            forwardButton.click();
            textView.shouldHave(text("Reading lists with sync"));
        });
        step("Нажатие кнопки продолжить и проверка четвертого экрана", () -> {
            forwardButton.click();
            textView.shouldHave(text("Send anonymous data"));
        });
        step("Нажатие кнопки начать и проверка наличия строки поиска", () -> {
            getStartedButton.click();
            searchContainer.shouldBe(visible);
        });
    }

    @Test
    @Tag("mobile")
    @JiraIssue("AUTO-1451")
    @DisplayName("Проверка настроек приложения")
    @Feature("Настройки")
    void settingsWikipediaTest() {
        step("Подождать загрузки приложения", () -> {
            textView.shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Нажатие кнопки назад. Пропуск начальных экранов.", () -> {
            back();
        });
        step("Нажатие иконки настроек", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_icon")).click();
        });
        step("Выбор пункта настройки из появившегося меню", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
        });
        step("Проверка, изменения языка на английский", () -> {
            $(MobileBy.id("android:id/summary"))
                    .shouldHave(text("English"));
        });
    }
}
