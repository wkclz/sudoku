package com.wkclz.sudoku;

public class Test {

    public static void main(String[] args) {
        test2();
    }



    private static void test2(){
        int count = 5;
        int x = count >> 2;
        System.out.println(x);
    }



    private static void test1(){
        int   count = 0;
        count += 1 << 0;
        count += 1 << 1;
        count += 1 << 2;
        count += 1 << 3;
        count += 1 << 4;
        count += 1 << 5;
        count += 1 << 6;
        count += 1 << 7;
        count += 1 << 8;
        System.out.println(count);
    }


}
