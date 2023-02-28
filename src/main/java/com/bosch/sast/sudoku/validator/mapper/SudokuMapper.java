package com.bosch.sast.sudoku.validator.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.model.Board;
import com.bosch.sast.sudoku.validator.service.ValidatorServiceImpl;

/**
 * As H2 DB doesn't really allow storing the "grid" IE the 2d arrays we will
 * need some trickery to map between the DTOs and entities.
 * Any ideas how to make it smarter?
 */

/**
 * SudokuMapper class executes methods convertToDto, to map the DTO and entities.
 * 
 * @author DineshKumar Chandrasekar
 */
@Component
public class SudokuMapper {
	public BoardDTO convertToDto(Board boarddata) {
		  BoardDTO boardDto = new ModelMapper().map(boarddata, BoardDTO.class);
		  boardDto.setGrid(new ValidatorServiceImpl().convertListTo2DArray(boarddata.getCells()));
		  boardDto.setId(boarddata.getId());
		  return boardDto;
		}
}
