package com.wkclz.sudoku.fun;


import com.wkclz.sudoku.utils.Print;
import com.wkclz.sudoku.utils.Range;

/**
 * 禁用不可能的值，得到唯一值
 */
public class Disable {


    private static int[][] sudoku = {
        {0,0,0,  0,0,0,   0,0,0},
        {0,0,3,  0,0,0,   0,0,0},
        {0,0,0,  0,9,0,   0,0,0},

        {0,0,0,  0,0,0,   0,0,0},
        {0,0,0,  0,0,0,   0,0,0},
        {9,0,0,  0,0,0,   0,0,0},

        {0,9,0,  0,0,0,   0,0,0},
        {0,0,0,  0,0,0,   0,0,0},
        {0,0,0,  0,0,0,   0,0,0},
    };
    // sudoku[0][2] = 9

    public static void main(String[] args) {
        Print.printSudoku(sudoku);
        int onlyYou = Disable.handle(sudoku);
        System.out.println("计算出 " + onlyYou + " 个数");
        Print.printSudoku(sudoku);
    }





    /**
     * 遍历要执行的数据
     * @param sudoku
     */
    public static int handle(int[][] sudoku){
        int count = 0;

        for (int f = 1; f <= 9; f++) {
            // 1. 所有不可能为 f 的，全部禁用
            disable(sudoku, f);
            // 2. 唯一区间只有一个不被禁用，值就是 f
                // 遍历区间，空位唯一就设置值
            // 3. 启用 未能确认的区域
            enableField(sudoku);
        }
        return count;

    }


    private static void disable(int[][] sudoku, int f){
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                int ij = sudoku[i][j];
                // 确认要禁用的对象
                if (ij == f){
                    disableByField(sudoku, i, j, f);
                }
            }
        }
    }



    private static void disableByField(int[][] sudoku, int i, int j, int f){
        // 区间检测，如果存在一个，全部禁用
        int[] rowRange = Range.getRowRange(i, j);
        int[] cloumnRange = Range.getCloumnRange(i, j);
        int[] palaceRange = Range.getPalaceRange(i, j);

        if (rangeCheck(sudoku, rowRange, f)){ disableByRange(sudoku, rowRange); }
        if (rangeCheck(sudoku, cloumnRange, f)){ disableByRange(sudoku, cloumnRange); }
        if (rangeCheck(sudoku, palaceRange, f)){ disableByRange(sudoku, palaceRange); }
    }


    /**
     * 区间禁用
     * @param sudoku
     * @param range
     */
    private static void disableByRange(int[][] sudoku,  int[] range){
        for (int r = range[1]; r <= range[3]; r++ ){
            for (int c = range[0]; c <= range[2]; c++ ){
                // 禁用
                int cell = sudoku[r][c];
                if (cell == 0 ){
                    sudoku[r][c] = -1;
                }
            }
        }
    }


    /**
     * 区间值检测，是否存在
     * @param sudoku
     * @param range
     * @param f
     * @return
     */
    private static boolean rangeCheck(int[][] sudoku,  int[] range, int f){
        for (int r = range[1]; r <= range[3]; r++ ){
            for (int c = range[0]; c <= range[2]; c++ ){
                // 改变值
                int cell = sudoku[r][c];
                if (cell == f ){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 启用所有被禁用的值
     * @param sudoku
     */
    private static void enableField(int[][] sudoku){
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][i] == -1){
                    sudoku[i][i] = 0;
                }
            }
        }
    }


}
