package com.driver;

import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {
    public static void main(String[] args) throws Exception {
        int digits = 7;
        int sum = 7;
        String result = "";
        if(9*digits<sum){
            throw new Exception("Account Number can not be generated");
        }
        while(digits>0 && sum>=0){
            if(sum==0){
                result += "0";
            }
            else if(sum<9){
                result += sum;
                sum = 0;
            }
            else{
                result += 9;
                sum = sum-9;
            }
            digits = digits-1;
        }
        System.out.println(result);
    }
}