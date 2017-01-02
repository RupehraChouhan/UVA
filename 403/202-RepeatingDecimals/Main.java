package com.company;
import java.util.ArrayList;
import java.util.Scanner;

class Main{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> testCase;
        while(sc.hasNext()) {
            testCase = new ArrayList<Integer>();
            testCase.add(sc.nextInt());
            testCase.add(sc.nextInt());
            input.add(testCase);
        }
        for(int i=0; i<input.size();i++) {
            repeatingDecimals(input.get(i));
        }
    }

    private static void repeatingDecimals(ArrayList<Integer> input) {
        int originalNum = input.get(0);
        int originDenominator = input.get(1);
        int openBracketIndex, repition;
        int numerator = originalNum;
        int denominator = originDenominator;
        ArrayList<Integer> setOfNumerators = new ArrayList<>();
        ArrayList<Integer> quotients = new ArrayList<Integer>();
        ArrayList<Integer> set = new ArrayList<Integer>();
        int zerosBorrowed = 0;
        boolean repeating = false;
        if(numerator ==0)
            quotients.add(0);
        while(repeating == false ) {
            if(set.contains(numerator/10) ==false )
                set.add(numerator);
            if(numerator<denominator) {
                if ((setOfNumerators.contains(numerator)==false) || zerosBorrowed>0) {
                    if(setOfNumerators.contains(numerator) == false)
                        setOfNumerators.add(numerator);
                    if (quotients.contains(-1) == false) {
                        quotients.add(-1);
                        numerator *= 10;
                        zerosBorrowed++;
                    } else if (zerosBorrowed == 0) {
                        numerator *= 10;
                        zerosBorrowed++;
                    } else if(zerosBorrowed > 0) {
                        if(zerosBorrowed ==1 && numerator==0) {
                            quotients.add(0);
                            break;
                        }
                        quotients.add(0);
                        numerator *= 10;
                    }
                    else{}
                }
                else {
                    repeating = true;
                    if(numerator == 0) {
                        quotients.add(0);
                    }

                }
            }
            else {
                if ((setOfNumerators.contains(numerator) ==false)) {
                    setOfNumerators.add(numerator);
                } else {
                    repeating = true;
                    if(quotients.indexOf(numerator/denominator)<quotients.indexOf(-1)) {
                        quotients.add(numerator/denominator);
                        numerator  = numerator % denominator;
                    }
                    break;
                }
                quotients.add(numerator/denominator);
                numerator = numerator % denominator;
                zerosBorrowed = 0;
            }
        }
        ArrayList<Integer> afterDecimal = new ArrayList<Integer>();
        for(int i=quotients.indexOf(-1)+1; i<quotients.size();i++){
            afterDecimal.add(quotients.get(i));
        }
        System.out.println("AfterDecima: "+ afterDecimal);
        if(quotients.get(0) == -1)
            quotients.add(0,0);
        openBracketIndex = quotients.indexOf(numerator/denominator);//
        if(numerator<denominator) {
            numerator *= 10;
            openBracketIndex = afterDecimal.indexOf(numerator / denominator);
            if (set.get(set.size() - 1) == numerator / 10 /10)
                openBracketIndex +=1;
        }
        if(numerator ==0)
            openBracketIndex = afterDecimal.lastIndexOf(0);
        System.out.print(originalNum+ "/"+ originDenominator+ " = ");
        //print all the numbers before decimal
        for(int i=0;i<=quotients.indexOf(-1); i++) {
            if(i == quotients.indexOf(-1))
                System.out.print(".");
            else
                System.out.print(quotients.get(i));
        }
        //print all the numbers after the decimal
        for(int i=0; i<afterDecimal.size(); i++) {
            if(i == openBracketIndex)
                System.out.print("(");
            if(i==50) {
                System.out.print("...");
                break;
            }
            System.out.print(afterDecimal.get(i));
        }
        repition = afterDecimal.size()-openBracketIndex;
        System.out.println(")");
        System.out.println("   "+repition+ " = number of digits in repeating cycle");
        System.out.println();
    }
}
