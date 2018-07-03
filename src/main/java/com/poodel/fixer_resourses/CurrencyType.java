package com.poodel.fixer_resourses;
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
        ZAR("ZAR"),
        EUR("EUR");

        private CurrencyType(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public static CurrencyType getCurrencyType(String abbreviation){
            for (CurrencyType ct: CurrencyType.values()) {
                if(abbreviation.equals(ct.getAbbreviation())){
                    return ct;
                }
            }
            return null;
        }

        private String abbreviation;
    }