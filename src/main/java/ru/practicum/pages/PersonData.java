package ru.practicum.pages;

public class PersonData {
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;

    public PersonData(String name, String surname, String address, String metroStation, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
    }

    // Геттеры для доступа к данным
    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public String getAddress() {

        return address;
    }

    public String getMetroStation() {

        return metroStation;
    }

    public String getPhone() {

        return phone;
    }
}