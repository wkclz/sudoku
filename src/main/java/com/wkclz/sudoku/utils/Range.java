package com.wkclz.sudoku.utils;

public class Range {

    /**
     * 获取行扫描的左上右下
     */
    public static int[] getRowRange(int i, int j){
        int[] range = {0, i, 8, i};
        return range;
    }
    /**
     * 获取列扫描的左上右下
     */
    public static int[] getCloumnRange(int i, int j){
        int[] range = {j, 0, j, 8};
        return range;
    }
    /**
     * 获取9宫扫描的左上右下
     */
    public static int[] getPalaceRange(int i, int j){
        int l = 0;
        int u = 0;
        int r = 0;
        int d = 0;

        // 行
        if (i < 3){
            u = 0;
            d = 2;
        }
        if (3 <= i && i < 6){
            u = 3;
            d = 5;
        }
        if (6 <= i){
            u = 6;
            d = 8;
        }

        // 列
        if (j < 3){
            l = 0;
            r = 2;
        }
        if (3 <= j && j < 6){
            l = 3;
            r = 5;
        }
        if (6 <= j){
            l = 6;
            r = 8;
        }

        int[] range = {l, u, r, d};
        return range;
    }

}
