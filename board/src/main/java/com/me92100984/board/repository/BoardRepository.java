package com.me92100984.board.repository;

import java.beans.Transient;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.board.domain.entity.Board;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
  @Query("select b from Board b where b.bno > 0")
  public List<Board> list();

  @Modifying
  @Transactional
  @Query("update Board b set b.title = :title, b.content = :content where b.bno = :bno")
  void modify(@Param("bno") Long bno, @Param("title") String title, @Param("content") String content);

  void deleteById(Long bno);
  
}
