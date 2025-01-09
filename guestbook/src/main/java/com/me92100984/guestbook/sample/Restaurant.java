package com.me92100984.guestbook.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

// setter 주입 방식
@Component
@Data
public class Restaurant {
  @Setter(onMethod_ = @Autowired)
  private Chef chef;
  
}

// 생성자 주입 방식

// @Component
// @Data
// @RequiredArgsConstructor -> @NonNull이나 final인 인스턴수 변수에 대한 생성자를 만듦.
// public class Restaurant {
//     private final Chef chef; // }
