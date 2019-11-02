# sudoku 数读

sudoku 一个5元钱的项目。

### 使用方法：
1. com.wkclz.sudoku.Sudoku.sudoku 定义原始数据【0表示空】
2. 执行 com.wkclz.sudoku.Sudoku.main 进行计算，控制台打印出结果
3. 未完成

### 计算过程：
1. 【单元格】计算一个区间内，已有8位数字，补全第9个
2. 【单元格】计算三个关联区间内，已出现8个数字，，补全第9个
3. 【单个数字】已出现的数字，对相联区间进行失效标记，再扫描全宫格，若存在唯一未失效，补全值
99. 【全局】考虑到以上过程，每一个值改变，都可能影响全局，将在任何一个值改变的情况下，化归运算


### 概念
- 单元格：一个小格
- 区间： 数字不可重复的单元
- 关联区间：一个单元格会受到多个区间的影响，这样的多个区间，叫关联区间
- 失效：在当次尝试计算时，确认不可能为某个值，标记为失效。下一次计算将清除标记

### 待续
> 目前只考虑了三种情况，更多可能性将继续完善...