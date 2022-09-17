package ru.netology.web;


import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WebInterfacesTest {

    @BeforeEach
    public void setUp() {
        Selenide.open("http://localhost:9999");
    }

    @Test
    void shouldTestAllIsRight() {

        $("[name=\"name\"]").setValue("Иванова-Петрова Анна Мария");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=\"order-success\"]").shouldHave(exactText("Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время.")).should(visible, Duration.ofSeconds(5));

    }

//    Далее негативные тесты

    @Test
    void shouldTestNoName() {

        $("[name=\"name\"]").setValue("");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=\"name\"].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldTestEnglishName() {

        $("[name=\"name\"]").setValue("Smith John");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=\"name\"].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы.")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldTestNoPhone() {

        $("[name=\"name\"]").setValue("Иванова-Петрова Анна Мария");
        $("[name=\"phone\"]").setValue("");
        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldTestPhoneMoreThan11Numbers() {

        $("[name=\"name\"]").setValue("Иванова-Петрова Анна Мария");
        $("[name=\"phone\"]").setValue("+790012345678");
        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=\"phone\"].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678.")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void shouldTestNoAgreement() {

        $("[name=\"name\"]").setValue("Иванова-Петрова Анна Мария");
        $("[name=\"phone\"]").setValue("+79001234567");
//        $(".checkbox__box").click();
        $("button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй")).should(visible, Duration.ofSeconds(5));

    }
}
