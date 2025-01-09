package com.me92100984.guestbook.domain;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
  private String title;

  // sample/ex03?title=test&dueDate=2025/01/08 => TodoDTO(title=test, dueDate=Wed Jan 08 00:00:00 KST 2025)
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date dueDate;
}
