//package com.poodel.fixer_resourses;
//public enum CurrencyType {
//        "AUD",
//        "BGN",
//        "BRL",
//        "CAD",
//        "CHF",
//        "CNY",
//        "CZK",
//        "DKK",
//        "GBP",
//        "HKD",
//        "HRK",
//        "HUF",
//        "IDR",
//        "ILS",
//        "INR",
//        "JPY",
//        "KRW",
//        "MXN",
//        "MYR",
//        "NOK",
//        "NZD",
//        "PHP",
//        "PLN",
//        "RON",
//        "RUB",
//        "SEK",
//        "SGD",
//        "THB",
//        "TRY",
//        "USD",
//        "ZAR",
//        "EUR";
//
//        private CurrencyType(String abbreviation) {
//            this.abbreviation = abbreviation;
//        }
//
//        public String getAbbreviation() {
//            return abbreviation;
//        }
//
//        public static Boolean checkCurrency(String curr){
//                for (CurrencyType ct: CurrencyType.values()) {
//                        if(curr.equals(ct.getAbbreviation())){
//                                return true;
//                        }
//        }
////        public static CurrencyType getCurrencyType(String abbreviation){
////            for (CurrencyType ct: CurrencyType.values()) {
////                if(abbreviation.equals(ct.getAbbreviation())){
////                    return ct;
////                }
////            }
////            return null;
////        }
//
//
//    }
//}