package com.stutern.cryptocurrencyapp.utilities;

public class StringUtil {

    public static String nameAndSymbol(String name, String symbol) {
        return name + " - " + symbol;
    }
    public static String appendDollarToPrice(String price) {return "$" + price;}
    public static String threeCharSymbol(String symbol) {
        if (symbol.length() <= 3)
            return symbol;
        else {
            return symbol.substring(0, 3);
        }
    }

}
