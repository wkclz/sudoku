package com.wkclz.sudoku.fun;


import com.wkclz.sudoku.utils.Print;

/**
 * 一行/列/阵上只差一个值
 */
public class OnlyYou {


    private static int[][] sudoku = {
            {1,2,3,  4,0,6,   7,8,9},
            {9,0,0,  0,0,0,   0,0,0},
            {8,0,0,  0,0,0,   0,0,0},

            {7,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},
            {5,0,0,  0,0,0,   0,0,0},

            {4,0,0,  0,0,0,   5,4,3},
            {3,0,0,  0,0,0,   6,9,2},
            {2,0,0,  0,0,0,   0,8,1},
    };

    public static void main(String[] args) {
        Print.printSudoku(sudoku);
        // 最简单的算法：唯一法
        OnlyYou.onlyYou(sudoku);
        Print.printSudoku(sudoku);
    }





    /**
     * 遍历要执行的数据
     * @param sudoku
     */
    public static void onlyYou(int[][] sudoku){
        for (int i = 0; i < sudoku.length; i++) {
            int[] ku = sudoku[i];
            for (int j = 0; j < ku.length; j++) {
                calc(sudoku, getRowRange(i, j), i, j);
                calc(sudoku, getCloumnRange(i, j), i, j);
                calc(sudoku, getPalaceRange(i, j), i, j);
            }
        }
    }

    /**
     * 计算区域内的值
     * @param sudoku
     * @param range 区域，左上右下
     * @param i
     * @param j
     */
    private static void calc(int[][] sudoku,  int[] range, int i, int j){
        int cell = sudoku[i][j];
        if (cell != 0){
            return;
        }
        int count = 511;

        // 找到区间内的所有非0 的值，求0所在位置理论值的和
        for (int r = range[1]; r <= range[3]; r++ ){
            for (int c = range[0]; c <= range[2]; c++ ){
                cell = sudoku[r][c];
                if (cell != 0){
                    // cell 值转换成位移，要减1
                    count -= (1 << (cell-1));
                }
            }
        }

        // 0所在位置，恰好为0的n 次幂，则成功，否则仍然是0
        int power = getPower(count);
        sudoku[i][j] = power + 1;
    }


    /**
     * 获取行扫描的左上右下
     */
    private static int[] getRowRange(int i, int j){
        int[] range = {0, i, 8, i};
        return range;
    }
    /**
     * 获取列扫描的左上右下
     */
    private static int[] getCloumnRange(int i, int j){
        int[] range = {j, 0, j, 8};
        return range;
    }
    /**
     * 获取9宫扫描的左上右下
     */
    private static int[] getPalaceRange(int i, int j){
        int l = 0;
        int u = 0;
        int r = 0;
        int d = 0;

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

        if (j < 3){
            l = 0;
            r = 2;
        }
        if (3 <= i && i < 6){
            l = 3;
            r = 5;
        }
        if (6 <= i){
            l = 6;
            r = 8;
        }

        int[] range = {l, u, r, d};
        return range;
    }



    // 获取幂
    private static int getPower(int count){
        for (int i = 0; i < 9; i++){
            int tmp = 1 << i;
            if (tmp == count){
                return i;
            }
        }
        return -1;
    }

}
