package com.bosch.sast.sudoku.validator.controller;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.service.ValidatorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorControllerImpl implements ValidatorController {

	@Autowired
	ValidatorServiceImpl validatorServiceImpl;
	
	
  @Override
  public BoardDTO getBoard(long id) {
    return validatorServiceImpl.getBoard(id);
  }

  @Override
  public boolean validateBoard(long id) {
    return validatorServiceImpl.isValidSudoku(id);
  }

  @Override
  public BoardDTO addBoard(NewBoardDTO newBoardDTO) {
    return validatorServiceImpl.saveBoard(newBoardDTO);
  }
  
}
