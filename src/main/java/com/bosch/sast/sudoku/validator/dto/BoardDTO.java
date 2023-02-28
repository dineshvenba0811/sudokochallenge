package com.bosch.sast.sudoku.validator.dto;

public class BoardDTO {

  private long id;
  private int[][] grid;

  public int[][] getGrid() {
    return grid;
  }

  public BoardDTO setGrid(int[][] grid) {
    this.grid = grid;
    return this;
  }

  public long getId() {
    return id;
  }

  public BoardDTO setId(long id) {
    this.id = id;
    return this;
  }
}
