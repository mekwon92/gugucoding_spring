package com.me92100984.guestbook.sample;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.ToString;
//묵시적 
@Component
@ToString
@Getter
public class Hotel {
  private Chef chef;
  
  public Hotel(Chef chef) {
    this.chef = chef;
  }
}
