package ru.yandex.scooter.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.scooter.model.Courier;
import ru.yandex.scooter.model.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierApiClient {

    private final String JSON = "application/json";
    private final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private final String CREATE_COURIER_PATH = "api/v1/courier";
    private final String LOGIN_COURIER_PATH = "api/v1/courier/login";
    private final String DELETE_COURIER_PATH = "api/v1/courier/";



    @Step ("Создание курьера")
    public ValidatableResponse createCourier (Courier courier) {
       return given().
               header("Content-type", JSON).
               body(courier).
               post(BASE_URL + CREATE_COURIER_PATH).
               then();
    }

    @Step ("Авторизация курьера")
    public ValidatableResponse loginCourier (CourierCredentials courierCredentials){
       return given().
                header("Content-type", "application/json").
                body(courierCredentials).
                post(BASE_URL+ LOGIN_COURIER_PATH).
                then();
    }

    @Step ("Удаление курьера")
    public ValidatableResponse deleteCourier (int id){
        return given().
                header("Content-type", "application/json").
                pathParam("id", id).
                delete(BASE_URL+ DELETE_COURIER_PATH + "{id}").
                then();
    }

}
