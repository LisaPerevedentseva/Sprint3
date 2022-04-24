package ru.yandex.scooter.model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    public String password;
    public String login;
    public String firstName;

    // конструктор со всеми параметрами
    public Courier(String login, String password, String firstName){
        this.login=login;
        this.password=password;
        this.firstName=firstName;
    }

    //конструктор без параметров для Gson
    public Courier(){}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
