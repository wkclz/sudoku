package com.wkclz.sudoku.utils;

public class Print {

    /**
     * 打印
     * @param sudoku
     */
    public static void printSudoku(int[][] sudoku){
        for (int i = 0; i < sudoku.length; i++) {
            System.out.print((i%3 == 0)? "\n\n":"\n");
            int[] ku = sudoku[i];
            for (int j = 0; j < ku.length; j++) {
                System.out.print((j%3 == 0)? "   ":" ");
                System.out.print(ku[j]);
            }
        }
        System.out.print("\n\n");
    }


}
