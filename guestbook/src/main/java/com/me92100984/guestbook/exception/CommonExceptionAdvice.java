package com.me92100984.guestbook.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
  
  // 예외상황 처리하기
  @ExceptionHandler(Exception.class)
  public String except(Exception ex, Model model) {
    log.error("EXCEPTION.."+ex.getMessage());
    model.addAttribute("exception", ex);
    log.error(model);
    return "error_page";
  }

  // custom 404페이지 처리하기
  @ExceptionHandler(NoHandlerFoundException.class)
  public String handle404(NoHandlerFoundException ex) {
    return "custom404";
  }
}
