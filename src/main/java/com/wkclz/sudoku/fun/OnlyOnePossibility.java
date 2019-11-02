package com.wkclz.sudoku.fun;


import com.wkclz.sudoku.utils.Print;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 一行/列/阵上只有一种可能
 */
public class OnlyOnePossibility {


    private static int[][] sudoku = {
        {0,0,0,  0,1,0,   3,0,0},
        {0,2,0,  0,0,0,   0,7,0},
        {0,0,0,  6,0,8,   0,0,0},

        {1,0,7,  5,0,0,   0,0,0},
        {0,0,0,  0,3,0,   0,9,4},
        {0,5,0,  0,9,0,   2,0,7},

        {0,1,0,  0,0,0,   8,0,0},
        {8,0,3,  0,4,0,   0,0,0},
        {0,0,0,  0,0,9,   4,6,0},
    };

    // sudoku[1][4] = 5
    // sudoku[3][6] = 6
    // sudoku[8][1] = 7

    public static void main(String[] args) {
        Print.printSudoku(sudoku);
        // 排除法，只有一种可能的场景
        int onePossibility = OnlyOnePossibility.handle(sudoku);
        System.out.println("计算出 " + onePossibility + " 个数");
        Print.printSudoku(sudoku);
    }





    /**
     * 遍历要执行的数据
     * @param sudoku
     */
    public static int handle(int[][] sudoku){
        int count = 0;
        for (int i = 0; i < sudoku.length; i++) {
            int[] ku = sudoku[i];
            for (int j = 0; j < ku.length; j++) {

                /*
                if (i == 1 && j == 4){
                    System.out.println("关注的内容：" + i + " " + j);
                }
                */

                Set<Integer> nums = new HashSet<>();
                // 找到所有已经有的值
                calc(sudoku, nums, getRowRange(i, j), i, j);
                calc(sudoku, nums, getCloumnRange(i, j), i, j);
                calc(sudoku, nums, getPalaceRange(i, j), i, j);

                // 如果已经有8个值了，就可以确定剩下的一个值
                if (nums.size() == 8){
                    Set<Integer> list = new HashSet<Integer>(){{add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);}};
                    list.removeAll(nums);
                    sudoku[i][j] = list.stream().collect(Collectors.toList()).get(0);
                    System.out.println("第 "+ i +" 行,第 "+j+" 列为 " + sudoku[i][j]);
                    count ++;
                }
                nums.clear();
            }
        }
        return count;
    }

    /**
     * 计算区域内的值
     * @param sudoku
     * @param range 区域，左上右下
     * @param i
     * @param j
     */
    private static void calc(int[][] sudoku,  Set<Integer> nums, int[] range, int i, int j){
        int cell = sudoku[i][j];
        if (cell != 0){
            return;
        }
        // 找出所有不重复的值
        for (int r = range[1]; r <= range[3]; r++ ){
            for (int c = range[0]; c <= range[2]; c++ ){
                cell = sudoku[r][c];
                if (cell != 0){
                    nums.add(cell);
                }
            }
        }
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
