package com.jengle.misc;

import java.math.BigDecimal; //do not forget the import statement

public class BigDecimalExample {
    public static void main(String[]args){
        
        BigDecimal pi = new BigDecimal("1314"); // displays 1314
        BigDecimal pi2 = new BigDecimal("13.14"); // displays 13.14
        
        System.out.println("no decimal point: " + pi);
        System.out.println("with decimal point: " + pi2);
    }
}