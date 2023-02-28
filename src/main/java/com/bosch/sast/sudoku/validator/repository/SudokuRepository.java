package com.bosch.sast.sudoku.validator.repository;

import com.bosch.sast.sudoku.validator.model.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SudokuRepository extends CrudRepository<Board, Long> {
    Optional<Board> findById(Long id);
}
