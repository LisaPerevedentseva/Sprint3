package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import ru.yandex.scooter.client.OrderApiClient;
import ru.yandex.scooter.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OrderStep {

    OrderApiClient orderApi = new OrderApiClient();


    @Step ("Получение id заказа")
    public int getOrderTrack (ValidatableResponse response){
        int track;
        return track=response.extract().path("track");
    }

    @Step ("Создаем рандомный заказ с цветом самоката {color}")
    public Order getRandomOrder (String[] color){
        String firstName= RandomStringUtils.randomAlphabetic(8);
        String lastName=RandomStringUtils.randomAlphabetic(8);
        String address=RandomStringUtils.randomAlphabetic(8) +"," + RandomStringUtils.randomNumeric(3);
        String metroStation=RandomStringUtils.randomNumeric(1);
        String phone="+7" + RandomStringUtils.randomNumeric(10);
        int rentTime= RandomUtils.nextInt(1, 7);
        String deliveryDate= DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        String comment=RandomStringUtils.randomAlphabetic(8);
        String[] colors=color;
        return new Order (firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colors);
    }


}
