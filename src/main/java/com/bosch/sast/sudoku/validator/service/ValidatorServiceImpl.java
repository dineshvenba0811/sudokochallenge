package com.bosch.sast.sudoku.validator.service;

import com.bosch.sast.sudoku.validator.dto.BoardDTO;
import com.bosch.sast.sudoku.validator.dto.NewBoardDTO;
import com.bosch.sast.sudoku.validator.mapper.SudokuMapper;
import com.bosch.sast.sudoku.validator.model.Board;
import com.bosch.sast.sudoku.validator.repository.SudokuRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ValidatorServiceImpl implementation class for the below operations
 * save the BoardDTO
 * validate the board
 * retrive the uploaded board
 * @author DineshKumar Chandrasekar
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {



	@Autowired
	SudokuRepository repo;
	
		/**
		 * isValidSudoku validate the board based on the given id by validating the below conditions.
		 * 9x9 grid 
		 * no repeating numbers in a row
		 * no repeating numbers in a row
		 * no repeating numbers in a column
		 * no repeating digit in a 3x3 sub-grid
		 * @param long boardId.
		 * @return boolean true if all the conditions are satisified or false.
		 */
  @Override
  public boolean isValidSudoku(long boardId) {
	  BoardDTO boardDto= getBoard(boardId);
	  boolean isValid = isValidSudoku(boardDto.getGrid()) ;
		if(isValid)	{
			return true;				
		}
		else {
			return false;
		}
  }

  
  /**
	 * isValidSudoku validate the board .
	 * @param int[][] grid.
	 * @return boolean true if all the conditions are satisified or false.
	 */
  @Override
  public boolean isValidSudoku(int[][] grid) {
	  
	  if (checkSize(grid) == false)
	    {
	        return false;
	    }
  // check rows/cols
  HashSet<Integer> rowSet = new HashSet<Integer>();
  HashSet<Integer> colSet = new HashSet<Integer>();
  for(int i = 0; i < 9; i++) {
      for(int j = 0; j < 9; j++) {
          // check row char
          int rowChar = grid[i][j];
          if(rowSet.contains(rowChar)) return false;
          if(rowChar >= 0 && rowChar <= 9) {
          	if(rowChar !=0)  rowSet.add(rowChar);                	
          }
          else {
          	return false;
          }
          
          // check col char
          int colChar = grid[j][i];
          if(colSet.contains(colChar)) return false;
          if(colChar >= 0 && colChar <= 9) {
          	if(colChar !=0)  colSet.add(colChar);                	
          }
          else {
          	return false;
          }
      }
      rowSet.clear();
      colSet.clear();
  }
  
  //check sub-boxes
  int rowMin = 0;
  int rowMax = 2;
  int colMin = 0; 
  int colMax = 2;
  for(int a = 0; a < 3; a++) {
      for(int b = 0; b < 3; b++) {
          for(int i = rowMin; i <= rowMax; i++) {
              for(int j = colMin; j <= colMax; j++) {
                  int c = grid[i][j];
                  if(rowSet.contains(c)) return false;
                  if(c >= 0 && c <=9) {
                  	if(c !=0)   rowSet.add(c);
                  }
                  else {
                  	return false;
                  }
              }
          }
          rowSet.clear();
          colMin += 3;
          colMax += 3;
      }
      rowMin += 3;
      rowMax += 3;
      colMin = 0;
      colMax = 2;
  }
  return true;
  }

  /**
	 * saveBoard save the board to the datbase .
	 * @param NewBoardDTO board.
	 * @return boolean true if all the conditions are satisified or false.
	 */
  @Override
  public BoardDTO saveBoard(NewBoardDTO newBoardDTO) {
	  BoardDTO boardDTOConverted=null;
		try {
				Board board = new Board();
				board.setCells(convert2DArrayToList(newBoardDTO));
				Board boarddata=repo.save(board);
				boardDTOConverted=new SudokuMapper().convertToDto(boarddata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  return boardDTOConverted;
	  
  }

  /**
 	 * getBoard retrive the uploaded board from the database.
 	 * @param long id.
 	 * @return BoardDTO .
 	 */
  @Override
  public BoardDTO getBoard(long id) {
	  Optional<Board> boardModel= repo.findById(id);
	  Board board=boardModel.get();
	  BoardDTO boardDto=new SudokuMapper().convertToDto(board);
    return boardDto;
  }
  
  public  List<Integer> convert2DArrayToList(NewBoardDTO newBoardDTO) {
		 List<List<Integer> > nestedLists =Arrays.stream(newBoardDTO.getGrid()).map(internalArray -> Arrays.stream(internalArray)
		         		.boxed()
		         		.collect(Collectors.toList())).collect(Collectors.toList());
		
		List<Integer> singleList=nestedLists.stream()
		         .flatMap(List::stream) 
		         .collect(Collectors.toList());
		
		return singleList;
		
	 }

	public  int[][] convertListTo2DArray(List<Integer> singleList ) {
	   int chunkSize=9;
	   int inputSize = singleList.size();
	   int chunkCount = (int) Math.ceil(inputSize / (double) chunkSize);
	
	   Map<Integer, List<Integer>> map = new HashMap<>(chunkCount);
	   List<List<Integer>> chunks = new ArrayList<>(chunkCount);
	   for (int i = 0; i < inputSize; i++) {
	       map.computeIfAbsent(i / chunkSize, (ignore) -> {
	           List<Integer> chunk = new ArrayList<>();
	           chunks.add(chunk);
	           return chunk;
	       }).add(singleList.get(i));
	   }
	   
	   int[][] arr = chunks.stream()
	   	    .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
	   	    .toArray(int[][]::new);
	   return arr;
	}
	
	public boolean checkSize(int[][] board)
	{
		   boolean flag = true;
		   int cols = board.length;
		   
		   for(int i=0 ; i<cols ; i++) {
			   if(board[i].length != 9) {
				   flag = false;
				   break;
			   }
		   }		          
	       if (cols == 9 && flag)
	       {
	           return true;
	       }   
	    return false;
	}
}
