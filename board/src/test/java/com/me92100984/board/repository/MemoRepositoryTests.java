package com.me92100984.board.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.board.domain.entity.Memo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemoRepositoryTests {
  @Autowired
  MemoRepository repository;

  @Test
  public void testClass() {
    System.out.println("here!" + repository.getClass().getName()); //jdk.proxy2.$Proxy140
    log.info(repository); // org.springframework.data.jpa.repository.support.SimpleJpaRepository@3bd69bf6
  }
  
  @Test
  public void testInsertDummies() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      Memo memo = Memo.builder().memoText("sample.." + i).build();
      repository.save(memo);
    });
  }

  @Test
  public void testSelect() {
    Long mno = 10L;
    Optional<Memo> result = repository.findById(mno);
    // Optional
    // Optional.of("Hello"); -> 값이 반드시 존재(null 전달시 NPE)
    // Optional.ofNullable(null); -> 값이 null인 경우, 빈 Optional 객체
    // String result = optional.orElse("Default Value"); -> 값이 없을 경우 기본값을 반환
    // String value = optional.orElseThrow(() -> new RuntimeException("Value not found")); -> 값이 없을 경우 예외를 던지는 기능
    if(result.isPresent()) {
      Memo memo = result.get(); // 값이 없으면 NoSuchElementException
      log.info(memo);
    }

    
  }

  @Test
  @Transactional
  public void testSelect2() {
    Long mno = 10L;
    Memo memo = repository.getOne(mno);
    log.info("===================");
    log.info(memo);
  }

  @Test
  public void testUpdate() {
    Memo memo = Memo.builder().mno(9L).memoText("수정된 9번 글").build();
    log.info(repository.save(memo)); //이건 객체가 반환되는게 삭제는 뭐가 반환되는게 없나봄?!!!! 복습도 하기 다까먹~
  }

  // @Test
  // public void testDelete() {
  //   repository.deleteById(4L);
  //   log.info);
  // }
}
