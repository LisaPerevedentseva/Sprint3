import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.scooter.client.CourierApiClient;
import ru.yandex.scooter.model.Courier;
import ru.yandex.scooter.model.CourierCredentials;
import ru.yandex.scooter.steps.CourierStep;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest {

    CourierApiClient courierApi;

    CourierStep step = new CourierStep();

    ValidatableResponse response;

    Courier courier;

    CourierCredentials courierCredentials;

    int courierId;

    @Before
    public void setUp() {
        // создаем объект для работы с методами курьера
        courierApi = new CourierApiClient();

        // перед каждым тестом создаем курьера, определяем его id
        courier = CourierStep.getRandomCourier();
        courierApi.createCourier(courier);
        courierId = step.getCourierId(courier);
        // создаем объект с авторизационными данными курьера
        courierCredentials = new CourierCredentials(courier);

    }

    @After
    public void clearData(){
           if (courierId!=0){
                courierApi.deleteCourier(courierId);}
    }

    @Test
    @DisplayName ("Login courier with correct credentials")
    @Description("Проверяем код ответа 200 и id курьера при авторизации с корректными данными")
    public void loginCourierWithCorrectCredentialSuccess(){

        // вызываем метод авторизации с коректными кредами
        response = courierApi.loginCourier(courierCredentials);
        // проверяем код ответа и тело
        response.statusCode(HTTP_OK).and().body("id", notNullValue());

    }

    @Test
    @DisplayName("Login courier with incorrect login")
    @Description ("Проверяем код ответа 404 и сообщение Учетная запись не найдена при авторизации с некорректным логином")
    public void loginCourierWithIncorrectLoginFailure(){

        // меняем логин курьера
        courierCredentials.setLogin(RandomStringUtils.randomAlphabetic(10));
        // вызываем метод авторизации с некоректным логином
        response = courierApi.loginCourier(courierCredentials);
        // проверяем код ответа и тело
        response.statusCode(HTTP_NOT_FOUND).and().body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Login courier with incorrect password")
    @Description ("Проверяем код ответа 404 и сообщение Учетная запись не найдена при авторизации с некорректным паролем")
    public void loginCourierWithIncorrectPasswordFailure(){

        // меняем пароль
        courierCredentials.setPassword(RandomStringUtils.randomAlphabetic(10));
        // вызываем метод авторизации с некоректным логином
        response = courierApi.loginCourier(courierCredentials);
        // проверяем код ответа и тело
        response.statusCode(HTTP_NOT_FOUND).and().body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Login courier without login")
    @Description ("Проверяем код ответа 400 и сообщение Недостаточно данных для входа при авторизации без логина")
    public void loginCourierWithoutLoginFailure(){

        // меняем пароль
        courierCredentials.setLogin("");
        // вызываем метод авторизации с некоректным логином
        response = courierApi.loginCourier(courierCredentials);
        // проверяем код ответа и тело
        response.statusCode(HTTP_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Login courier without password")
    @Description ("Проверяем код ответа 400 и сообщение Недостаточно данных для входа при авторизации без пароля")
    public void loginCourierWithoutPasswordFailure(){

        // меняем пароль
        courierCredentials.setPassword("");
        // вызываем метод авторизации с некоректным логином
        response = courierApi.loginCourier(courierCredentials);
        // проверяем код ответа и тело
        response.statusCode(HTTP_BAD_REQUEST).and().body("message", equalTo("Недостаточно данных для входа"));
    }

}
