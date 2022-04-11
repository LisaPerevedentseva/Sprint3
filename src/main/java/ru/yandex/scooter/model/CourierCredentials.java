package ru.yandex.scooter.model;

public class CourierCredentials {

    public String login;
    public String password;

    public CourierCredentials (){}

    // конструктор с параметром, логопасс формируется из данных объекта "Курьер", к-рый создается за пределами класса
    public CourierCredentials (Courier courier) {
        this.login= courier.login;
        this.password=courier.password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
