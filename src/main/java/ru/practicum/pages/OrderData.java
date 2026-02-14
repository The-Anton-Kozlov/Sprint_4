package ru.practicum.pages;

public class OrderData {
    private String deliveryDate;
    private String scooterColour;
    private String comment;

    public OrderData(String deliveryDate, String scooterColour, String comment) {
        this.deliveryDate = deliveryDate;
        this.scooterColour = scooterColour;
        this.comment = comment;
    }

    public String getDeliveryDate() {

        return deliveryDate;
    }

    public String getScooterColour() {

        return scooterColour;
    }

    public String getComment() {

        return comment;
    }
}
