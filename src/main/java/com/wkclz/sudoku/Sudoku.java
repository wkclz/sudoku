package com.wkclz.sudoku;

import com.wkclz.sudoku.fun.OnlyYou;
import com.wkclz.sudoku.utils.Print;

public class Sudoku {



    private static int[][] sudoku = {
            {0,0,0,  0,0,0,   0,0,0},
            {0,0,3,  4,0,1,   2,0,0},
            {0,7,0,  0,9,0,   0,3,0},

            {0,0,6,  7,0,8,   9,0,0},
            {0,2,0,  0,0,0,   0,5,0},
            {0,0,1,  2,0,3,   4,0,0},

            {0,9,0,  0,6,0,   0,7,0},
            {0,0,2,  1,0,4,   3,0,0},
            {0,0,0,  0,0,0,   0,0,0},
    };

    public static void main(String[] args) {
        Print.printSudoku(sudoku);

        // 最简单的算法：唯一法
        OnlyYou.onlyYou(sudoku);
        Print.printSudoku(sudoku);


    }






    private static int[][] xxx = {
            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},

            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},

            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},
            {0,0,0,  0,0,0,   0,0,0},
    };


}
