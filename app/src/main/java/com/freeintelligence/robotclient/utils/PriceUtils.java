package com.freeintelligence.robotclient.utils;

import java.math.BigDecimal;

/**
 * Created by za on 2018/7/26.
 */

public class PriceUtils {
    public static String getprice(double price){
        if(price<10000){
            return  price+"元";
        }else {
            double v = price / 10000;
            double value =new BigDecimal(v).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            return value+"万";
        }
    }
    public static String getMile(double mile){
        if(mile<1000){
            return  mile+"米";
        }else {
            double v = mile / 1000;
            double value =new BigDecimal(v).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            return value+"千米";
        }
    }
}
