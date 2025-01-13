package com.me92100984.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.board.domain.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>{
  
}
