package com.alexlis.tests.local;

import com.alexlis.annotations.JiraIssue;
import com.alexlis.annotations.Layer;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

@Owner("zatulivetrova")
@Story("wikipedia tests")
@Layer("mobile")
public class AndroidTests extends TestBase {

    SelenideElement textView = $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView")),
            forwardButton = $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            getStartedButton = $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")),
            searchContainer = $(MobileBy.id("org.wikipedia.alpha:id/search_container"));

    @Test
    @Tag("mobile")
    @JiraIssue("")
    @DisplayName("Поиск в приложении")
    @Feature("поиск")
    void searchingWikipediaTest() {
        step("Подождать загрузки приложения", () -> {
            textView.shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Пропустить шаги", () -> {
            back();
        });
        step("Нажать на строку ввода", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        });
        step("Ввод текста в строку поиска", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });
        step("Проверяем, что поиск сработал", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(text("Appium"));
        });
    }

    @Test
    @Tag("mobile")
    @JiraIssue("")
    @DisplayName("Прохождение начального экрана")
    @Feature("начальный экран")
    void getStartedWikipediaTest() {
        step("Проверка текста на первом экране приложения", () -> {
            textView
                    .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Нажимаем кнопку продолжить и проверяем следуюущий экран", () -> {
            forwardButton.click();
            textView.shouldHave(text("New ways to explore"));
        });
        step("Нажимаем кнопку продолжить и проверяем третий экран", () -> {
            forwardButton.click();
            textView.shouldHave(text("Reading lists with sync"));
        });
        step("Нажимаем кнопку продолжить и проверяем четвертый экран", () -> {
            forwardButton.click();
            textView.shouldHave(text("Send anonymous data"));
        });
        step("Нажимаем кнопку Начать и проверяем наличие строки поиска", () -> {
            getStartedButton.click();
            searchContainer.shouldBe(visible);
        });
    }

    @Test
    @Tag("mobile")
    @JiraIssue("")
    @DisplayName("Проверка настроек приложения")
    @Feature("настройки")
    void settingsWikipediaTest() {
        step("Подождать загрузки приложения", () -> {
            textView.shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });
        step("Пропустить шаги", () -> {
            back();
        });
        step("Нажимаем на иконку настроек", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_icon")).click();
        });
        step("Выбираем пункт настройки из появившегося меню", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/main_drawer_settings_container")).click();
        });
        step("Проверяем, что установлен английский язык", () -> {
            $(MobileBy.id("android:id/summary"))
                    .shouldHave(text("English"));
        });
    }
}
