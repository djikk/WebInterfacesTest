package ru.netology.web;


import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WebInterfacesTest {


    @Test
    void TheRightTest() {

        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("Анна Мария Иванова-Петрова");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $(".paragraph").shouldHave(exactText("Ваша заявка успешно отправлена!" +
                " Наш менеджер свяжется с вами в ближайшее время.")).should(visible, Duration.ofSeconds(5));

    }

//    Далее негативные тесты

    @Test
    void NoName() {
        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void PhoneMoreThan11Numbers() {

        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("Анна Мария Иванова-Петрова");
        $("[name=\"phone\"]").setValue("+790012345678");
        $(".checkbox__box").click();
        $("button").click();
        $("#root > div > form > div:nth-child(2) > span > span > span.input__sub").shouldHave(exactText("Телефон указан неверно." +
                " Должно быть 11 цифр, например, +79012345678.")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void NoPhone() {

        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("Анна Мария Иванова-Петрова");
        $("[name=\"phone\"]").setValue("");
        $(".checkbox__box").click();
        $("button").click();
        $("#root > div > form > div:nth-child(2) > span > span > span.input__sub").shouldHave(exactText("Поле обязательно для заполнения")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void EnglishName() {
        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("Smith John");
        $("[name=\"phone\"]").setValue("+79001234567");
        $(".checkbox__box").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы.")).should(visible, Duration.ofSeconds(5));
    }

    @Test
    void NoAgree() {

        Selenide.open("http://localhost:9999");
        $("[name=\"name\"]").setValue("Анна Мария Иванова-Петрова");
        $("[name=\"phone\"]").setValue("+79001234567");
//        $(".checkbox__box").click();
        $("button").click();
        $("#root > div > form > div:nth-child(3) > label > span.checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй")).should(visible, Duration.ofSeconds(5));

    }
}
