package com.poodel.commands;

/**
 * Перечисление доступных аббревиатур валют на сайте fixer.io
 */
public enum CurrencyType {
    AUD("AUD"),
    BGN("BGN"),
    BRL("BRL"),
    CAD("CAD"),
    CHF("CHF"),
    CNY("CNY"),
    CZK("CZK"),
    DKK("DKK"),
    GBP("GBP"),
    HKD("HKD"),
    HRK("HRK"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    INR("INR"),
    JPY("JPY"),
    KRW("KRW"),
    MXN("MXN"),
    MYR("MYR"),
    NOK("NOK"),
    NZD("NZD"),
    PHP("PHP"),
    PLN("PLN"),
    RON("RON"),
    RUB("RUB"),
    SEK("SEK"),
    SGD("SGD"),
    THB("THB"),
    TRY("TRY"),
    USD("USD"),
    UAH("UAH"),
    ZAR("ZAR"),
    EUR("EUR");

    /**
     * Конструктор Enum-а, предоставляющий доступ на запись содержимомого
     *
     * @param abbreviation - поле аббревиатуры
     */
    CurrencyType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Геттер Enum-a, предоставляющий доступ к содержимому из-вне
     *
     * @return возрат аббревиатуры
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Поле класса Аббревиатура.
     */
    private String abbreviation;
}
