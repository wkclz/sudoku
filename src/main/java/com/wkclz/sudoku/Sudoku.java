package com.wkclz.sudoku;

import com.wkclz.sudoku.fun.Disable;
import com.wkclz.sudoku.fun.OnlyOnePossibility;
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

        for (int i = 0;; i++) {
            int count = 0;
            // 最简单的算法：唯一法
            count += OnlyYou.handle(sudoku);
            // 排除法，只有一种可能的场景
            count +=  OnlyOnePossibility.handle(sudoku);
            // 禁用法，禁用不可能的值，确认唯一性
            count +=  Disable.handle(sudoku);

            System.out.println("====== 第 " + i + " 次场景遍历，结果数为 " + count + " ======");
            if (count == 0){
                System.out.println("====== 遍历，无结果，计算完成！\n");
                break;
            }
        }

        int empty = Print.checkEmpty(sudoku);
        if (empty == 0){
            System.out.println("已完成所有计算！");
        } else {
            System.out.println("仍然存在 " + empty + " 个未完成的空位，请优化算法");
        }

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
