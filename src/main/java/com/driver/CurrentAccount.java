package com.driver;


import java.util.Arrays;
import java.util.PriorityQueue;

class Pair{
    char ch;
    int freq;

    public Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
public class CurrentAccount extends BankAccount{
    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    String tradeLicenseId; //consists of Uppercase English characters only



    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean valid = true;
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            char ch = tradeLicenseId.charAt(i);
            char ch1 = tradeLicenseId.charAt(i+1);
            if(ch==ch1){
                valid = false;
                break;
            }
        }
        if(!valid){
            int[] count = new int[28];
            for(int i=0;i<tradeLicenseId.length();i++){
                count[tradeLicenseId.charAt(i)-'A']++;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((p, q)->{
                return q.freq-p.freq;
            });
            for(int i=0;i<26;i++){
                if(count[i]>0){
                    pq.add(new Pair((char)(i+'A'),count[i]));
                }
            }
            int index = 0;
            char[] nums = new char[tradeLicenseId.length()];
            Pair prev = new Pair('c',-1);
            while(pq.size()>0){
                Pair p = pq.remove();
                char ch = p.ch;
                int freq = p.freq;
                nums[index] = ch;
                index++;
                p.freq =p.freq-1;

                if(prev.freq>0){
                    pq.add(prev);
                }
                prev = p;
            }
            if(index!=nums.length){
                throw new Exception("Valid License can not be generated");
            }
            else{
                tradeLicenseId = new String(nums);
            }
        }

    }

}
