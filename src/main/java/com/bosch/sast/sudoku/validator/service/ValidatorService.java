package com.bosch.sast.sudoku.validator.service;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;

public interface ValidatorService {

  boolean isValidSudoku(long boardId);

  boolean isValidSudoku(int[][] grid);

  BoardDTO saveBoard(NewBoardDTO board);

  BoardDTO getBoard(long id);
}
