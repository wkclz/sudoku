package com.wkclz.sudoku.algorithm;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sudoku
 * @description: 数独
 * @author: ponderChen
 * @create: 2021/06/11 13:58
 */
@Data
public class Sudoku {
    protected static final int NUMBER_OF_ROW = 9;
    protected static final int NUMBER_OF_COL = 9;
    protected static final int NUMBER_OF_BLOCK_ROW = 3;
    protected static final int NUMBER_OF_BLOCK_COL = 3;
    protected static final int NUMBER_OF_CELL = NUMBER_OF_ROW * NUMBER_OF_COL;
    /**
     * 定义9*9的数独
     */
    private Cell[][] cells = new Cell[NUMBER_OF_ROW][NUMBER_OF_COL];
    /**
     * 已经确定数字的格子数量
     */
    private int fixedNumber;

    public Sudoku() {
    }

    /**
     * 构造函数
     *
     * @param numbers 格子信息
     */
    public Sudoku(int[][] numbers) {
        fixedNumber = 0;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COL; j++) {
                cells[i][j] = new Cell(numbers[i][j]);
                if (cells[i][j].isFixed()) {
                    fixedNumber += 1;
                }
            }
        }
    }

    public Sudoku copy() {
        Sudoku sudoku = new Sudoku();
        sudoku.setFixedNumber(fixedNumber);
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COL; j++) {
                sudoku.getCells()[i][j] = cells[i][j].copy();
            }
        }
        return sudoku;
    }

    public void print() {
        System.out.println("--------------sudoku---------------");
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_COL; j++) {
                integers.add(cells[i][j].getNumber());
            }
            System.out.println(integers);
        }
    }
}
