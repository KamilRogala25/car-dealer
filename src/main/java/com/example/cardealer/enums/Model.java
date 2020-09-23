package com.example.cardealer.enums;

public enum  Model {
    BMW_X5("X5", Manufacturer.BWM), BMW_E36("E36", Manufacturer.BWM), BMW_E46("E46", Manufacturer.BWM),
    VOLVO_S40("S40", Manufacturer.VOLVO),VOLVO_V40("V40", Manufacturer.VOLVO),VOLVO_V50("V50", Manufacturer.VOLVO),
    VOLVO_S60("S60", Manufacturer.VOLVO),VOLVO_V60("V60", Manufacturer.VOLVO),VOLVO_S70("S70", Manufacturer.VOLVO),
    FIAT_500("500", Manufacturer.FIAT), FIAT_PUNTO("Punto", Manufacturer.FIAT), FIAT_BRAVO("Bravo", Manufacturer.FIAT), FIAT_DUCATO("Ducato", Manufacturer.FIAT);

    protected Manufacturer manufacturer;

    private final String text;

    Model(String text, Manufacturer manufacturer) {
        this.text = text;
        this.manufacturer =manufacturer;
    }

    @Override
    public String toString() {
        return text;
    }


}
