package ru.yandex.scooter.steps;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.scooter.client.CourierApiClient;
import ru.yandex.scooter.model.Courier;
import ru.yandex.scooter.model.CourierCredentials;

public class CourierStep {

    CourierApiClient courierApiClient = new CourierApiClient();
    CourierCredentials courierCredentials;

   // метод для получения id курьера
    @Step("Получение id курьера")
    public int getCourierId(Courier courier){
        int courierId;
        return courierId = courierApiClient.loginCourier(new CourierCredentials(courier)).extract().path("id");
    }

    @Step("Создаем курьера с рандомными данными")
    public static Courier getRandomCourier(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

}
