package com.bosch.sast.sudoku.validator.model;

import javax.persistence.*;
import java.util.List;

/**
 * As JPA/H2 doesn't easily let us save the 2d arrays, the sudoku
 * board will be saved as a simple list.
 */

@Entity
public class Board {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @ElementCollection
  private List<Integer> cells;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Integer> getCells() {
    return cells;
  }

  public void setCells(List<Integer> board) {
    this.cells = board;
  }

}
