package com.wkclz.sudoku.algorithm;

import static com.wkclz.sudoku.algorithm.Sudoku.*;

/**
 * @program: sudoku
 * @description: 求解器
 * @author: ponderChen
 * @create: 2021/06/11 15:05
 */
public class Sovler {


    /**
     * 确定格子所在方块
     *
     * @param row 行
     * @param col 列
     * @return 所在块
     */
    private static int rundBlockNumber(int row, int col) {
        return (row / NUMBER_OF_BLOCK_ROW) * NUMBER_OF_BLOCK_ROW + (col / NUMBER_OF_BLOCK_COL);
    }

    public static void main(String[] args) {
        int[][] mySuDoKu = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 4, 0, 1, 2, 0, 0},
            {0, 7, 0, 0, 9, 0, 0, 3, 0},

            {0, 0, 6, 7, 0, 8, 9, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 1, 2, 0, 3, 4, 0, 0},

            {0, 9, 0, 0, 6, 0, 0, 7, 0},
            {0, 0, 2, 1, 0, 4, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Sudoku sudoku = new Sudoku(mySuDoKu);
        Sovler sovler = new Sovler();
        sovler.baseExclusive(sudoku);
        sovler.findSudoKuSolution(sudoku, 0);
    }

    /**
     * 为数独寻找一个解
     *
     * @param sudoku  当前状态的数独
     * @param cellSeq 当前格子
     */
    public void findSudoKuSolution(Sudoku sudoku, int cellSeq) {
        if (sudoku.getFixedNumber() == NUMBER_OF_CELL) {
            //如果所有个格子都确定则，表示完成。输出结果
            sudoku.print();
        }
        // 找到一个还没有确定的格子
        cellSeq = findFirstNoFixedCell(sudoku, cellSeq);
        if (cellSeq == NUMBER_OF_CELL) {
            return;
        }
        // 进行递归搜索
        int row = cellSeq / NUMBER_OF_ROW;
        int col = cellSeq % NUMBER_OF_ROW;
        // 当前格子的状态
        Cell cell = sudoku.getCells()[row][col];
        // 遍历格子所有候选状态
        for (Integer number : cell.getCandidate()) {
            Sudoku copy = sudoku.copy();
            if (setCandidatorTofixed(copy, row, col, number)) {
                findSudoKuSolution(copy, cellSeq + 1);
            }
        }
    }

    /**
     * 为数独寻找一个解
     *
     * @param sudoku  当前状态的数独
     * @param cellSeq 当前格子
     */
    public int findFirstNoFixedCell(Sudoku sudoku, int cellSeq) {
        do {
            int row = cellSeq / NUMBER_OF_ROW;
            int col = cellSeq % NUMBER_OF_ROW;
            if (!sudoku.getCells()[row][col].isFixed()) {
                break;
            }
            cellSeq += 1;
        } while (cellSeq < NUMBER_OF_CELL);
        return cellSeq;
    }

    /**
     * 检查格子填写这个数是合法，检查20各个相关的格子
     *
     * @param sudoku 数独
     * @param row    格子所在行
     * @param col    格子所在列
     * @param number 格子填写数字
     * @return true 表示合法，否则为false
     */
    private boolean checkValidCandidator(Sudoku sudoku, int row, int col, int number) {
        // 检查行
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            if ((i != col) && (sudoku.getCells()[row][i].getNumber() == number)) {
                return false;
            }
        }
        // 检查列
        for (int i = 0; i < NUMBER_OF_COL; i++) {
            if ((i != row) && (sudoku.getCells()[i][col].getNumber() == number)) {
                return false;
            }
        }
        // 检查九方格
        int rows = (row / NUMBER_OF_BLOCK_ROW) * NUMBER_OF_BLOCK_ROW;
        int cols = (col / NUMBER_OF_BLOCK_COL) * NUMBER_OF_BLOCK_COL;
        for (int i = rows; i < NUMBER_OF_ROW; i++) {
            if (i - rows == 3) {
                break;
            }
            for (int j = cols; j < NUMBER_OF_COL; j++) {
                if (j - cols == 3) {
                    break;
                }
                if (!((i != row) && (j != col)) && (sudoku.getCells()[i][j].getNumber() == number)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据初始解进行排查
     *
     * @param sudoku 数独
     */
    private void baseExclusive(Sudoku sudoku) {
        for (int i = 0; i < NUMBER_OF_CELL; i++) {
            int row = i / NUMBER_OF_ROW;
            int col = i % NUMBER_OF_ROW;
            if (sudoku.getCells()[row][col].isFixed()) {
                singleRowExclusive(sudoku, row, sudoku.getCells()[row][col].getNumber());
                singleColumnExclusive(sudoku, col, sudoku.getCells()[row][col].getNumber());
                int block = rundBlockNumber(row, col);
                singleBlockExclusive(sudoku, block, sudoku.getCells()[row][col].getNumber());
            }
        }
    }

    /**
     * 所在行排查这个数字
     *
     * @param sudoku 数独
     * @param row    行
     * @param number 数字
     */
    private boolean singleRowExclusive(Sudoku sudoku, int row, int number) {
        for (int i = 0; i < NUMBER_OF_COL; i++) {
            if (sudoku.getCells()[row][i].isCellHasCandidator(number)) {
                if (!removeCellCandidator(sudoku, row, i, number)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 所在列排查这个数字
     *
     * @param sudoku 数独
     * @param col    列
     * @param number 数字
     */
    private boolean singleColumnExclusive(Sudoku sudoku, int col, int number) {
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            if (sudoku.getCells()[i][col].isCellHasCandidator(number)) {
                if (!removeCellCandidator(sudoku, i, col, number)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 所在块排查这个数字
     *
     * @param sudoku 数独
     * @param block  块
     * @param number 数字
     */
    private boolean singleBlockExclusive(Sudoku sudoku, int block, int number) {
        int r = (block / NUMBER_OF_BLOCK_ROW) * NUMBER_OF_BLOCK_ROW;
        int c = (block % NUMBER_OF_BLOCK_COL) * NUMBER_OF_BLOCK_COL;
        for (int i = 0; i < NUMBER_OF_BLOCK_ROW * NUMBER_OF_BLOCK_COL; i++) {
            int row = r + i / NUMBER_OF_BLOCK_ROW;
            int col = c + i % NUMBER_OF_BLOCK_ROW;
            if (sudoku.getCells()[row][col].isCellHasCandidator(number)) {
                if (!removeCellCandidator(sudoku, row, col, number)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 移除候选点
     *
     * @param sudoku
     * @param row
     * @param col
     * @param num
     * @return
     */
    private boolean removeCellCandidator(Sudoku sudoku, int row, int col, int num) {
        //判断是否确定
        if (!sudoku.getCells()[row][col].isFixed()) {
            // 如果没有确定
            sudoku.getCells()[row][col].getCandidate().removeIf(integer -> integer == num);
            // 若只剩下一个
            if (sudoku.getCells()[row][col].getCandidate().size() == 1) {
                if (!singlesCandidature(sudoku, row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否用固定的格子
     *
     * @param sudoku 数独
     * @param row    格子所在行
     * @param col    格子所在列
     * @return true 表示有，否则为false
     */
    private boolean singlesCandidature(Sudoku sudoku, int row, int col) {
        if (!switchCellToFixedSc(sudoku, row, col)) {
            return false;
        }
        if (!singleRowExclusive(sudoku, row, sudoku.getCells()[row][col].getNumber())) {
            return false;
        }

        if (!singleColumnExclusive(sudoku, col, sudoku.getCells()[row][col].getNumber())) {
            return false;
        }

        int block = rundBlockNumber(row, col);
        if (!singleBlockExclusive(sudoku, block, sudoku.getCells()[row][col].getNumber())) {
            return false;
        }
        sudoku.setFixedNumber(sudoku.getFixedNumber() + 1);
        return true;
    }

    /**
     * 是否可以在一个格子中暂时确定一个数
     *
     * @param sudoku 数独
     * @param row    行
     * @param col    列
     * @param number 数字
     * @return true 表示成功，否则为false
     */
    private boolean setCandidatorTofixed(Sudoku sudoku, int row, int col, int number) {
        // 判断该格子是否可以填充这个数字
        if (!switchCellToFixed(sudoku, row, col, number)) {
            return false;
        }
        // 如果可以则，排查相关格子候选点
        if (!singleRowExclusive(sudoku, row, number)) {
            return false;
        }

        if (!singleColumnExclusive(sudoku, col, number)) {
            return false;
        }

        int block = rundBlockNumber(row, col);
        if (!singleBlockExclusive(sudoku, block, number)) {
            return false;
        }
        sudoku.setFixedNumber(sudoku.getFixedNumber() + 1);
        return true;
    }

    /**
     * 判断是否可以固定一个确定的数字
     *
     * @param sudoku 数独
     * @param row    格子所在行
     * @param col    格子所在列
     * @return true 表示固定成功，否则为false
     */
    private boolean switchCellToFixedSc(Sudoku sudoku, int row, int col) {
        // 如果填充数字没有冲突，则固定这个数字
        if (checkValidCandidator(sudoku, row, col, sudoku.getCells()[row][col].getCandidate().get(0))) {
            sudoku.getCells()[row][col].setFixed(true);
            sudoku.getCells()[row][col].setNumber(sudoku.getCells()[row][col].getCandidate().get(0));
            sudoku.getCells()[row][col].getCandidate().clear();
            return true;
        }
        return false;
    }

    /**
     * 判断是否可以固定一个数字
     *
     * @param sudoku 数独
     * @param row    格子所在行
     * @param col    格子所在列
     * @return true 表示固定成功，否则为false
     */
    private boolean switchCellToFixed(Sudoku sudoku, int row, int col, int number) {
        // 如果填充数字没有冲突，则固定这个数字
        if (checkValidCandidator(sudoku, row, col, number)) {
            sudoku.getCells()[row][col].setFixed(true);
            sudoku.getCells()[row][col].setNumber(number);
            sudoku.getCells()[row][col].getCandidate().clear();
            return true;
        }
        return false;
    }


}
