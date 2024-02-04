package com.wkclz.sudoku.algorithm;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sudoku
 * @description: 单个格子
 * @author: ponderChen
 * @create: 2021/06/11 13:54
 */
@Data
public class Cell {
    /**
     * 数字是否确定
     */
    private boolean fixed;
    /**
     * 如果确定，则数字为
     */
    private int number;
    /**
     * 如果不确定，则候选数字
     */
    private List<Integer> candidate;

    public Cell() {
    }

    /**
     * 构造函数
     *
     * @param number
     */
    public Cell(int number) {
        if (number == 0) {
            fixed = false;
            this.number = 0;
            candidate = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                candidate.add(i);
            }
        } else {
            fixed = true;
            this.number = number;
            candidate = new ArrayList<>();
        }
    }

    /**
     * 由不确定状态转换为确定状态
     *
     * @param number 数字
     */
    public void switchToFixed(int number) {
        fixed = true;
        this.number = number;
        // 从候选集中删除
        candidate.clear();
    }

    public boolean isCellHasCandidator(int number) {
        if (fixed) {
            return false;
        }
        if (candidate.contains(number)) {
            return true;
        } else {
            return false;
        }
    }

    public Cell copy() {
        Cell cell = new Cell();
        cell.fixed = fixed;
        cell.number = number;
        cell.candidate = new ArrayList<>(candidate);
        return cell;
    }
}
