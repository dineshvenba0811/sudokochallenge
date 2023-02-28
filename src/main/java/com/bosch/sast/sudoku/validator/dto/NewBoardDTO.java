package com.bosch.sast.sudoku.validator.dto;

public class NewBoardDTO {

  private int[][] grid;

  public int[][] getGrid() {
    return grid;
  }

  public NewBoardDTO setGrid(int[][] grid) {
    this.grid = grid;
    return this;
  }
}
