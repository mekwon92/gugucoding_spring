package com.me92100984.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.board.domain.entity.Board;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
  @Autowired
  private BoardRepository repository;
  

  @Test
  @Transactional
  public void testInsert() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Board board = Board.builder().title("title"+i).content("content"+i).writer("user"+i).build();
      repository.save(board);
    });
  }




}
