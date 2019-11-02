package com.wkclz.sudoku.fun;


import com.wkclz.sudoku.utils.Print;
import com.wkclz.sudoku.utils.Range;

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
    // sudoku[0][4] = 5
    // sudoku[4][0] = 6
    // sudoku[8][6] = 7

    public static void main(String[] args) {
        Print.printSudoku(sudoku);
        int onlyYou = OnlyYou.handle(sudoku);
        System.out.println("计算出 " + onlyYou + " 个数");
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
                count += calc(sudoku, Range.getRowRange(i, j), i, j);
                count += calc(sudoku, Range.getCloumnRange(i, j), i, j);
                count += calc(sudoku, Range.getPalaceRange(i, j), i, j);
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
    private static int calc(int[][] sudoku,  int[] range, int i, int j){
        int cell = sudoku[i][j];
        if (cell != 0){
            return 0;
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

        if (power == -1){
            return 0;
        }

        System.out.println("单区间唯一确定：第 "+ i +" 行,第 "+j+" 列为 " + sudoku[i][j]);
        return 1;
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
