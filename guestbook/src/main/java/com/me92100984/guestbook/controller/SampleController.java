package com.me92100984.guestbook.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me92100984.guestbook.domain.SampleDTO;
import com.me92100984.guestbook.domain.SampleDTOList;
import com.me92100984.guestbook.domain.TodoDTO;




@Controller
@RequestMapping("sample")
@Log4j2
public class SampleController {

  @RequestMapping("")
  public void basic(){
    log.info("basic..............................");
  }
  
  // 자동으로 DTO의 setter메서드가 작동하면서 파라미터를 수집하게 됨!
  // sample/ex01?name=aa&age=10 => SampleDTO(name=aa, age=10)
  @GetMapping("ex01") 
  public void ex01(SampleDTO dto) {
    log.info(""+dto);
  }
  
  // 이름이 달라도 name으로 파라미터를 받아올수있다
  // sample/ex02?name=123 =>123
  @GetMapping("ex02")
  public void ex02(@RequestParam("name") String id) {
    log.info(id);
  }

  // 같은 ids로 여러 값 불러오기
  // sample/ex02list?ids=123&ids=234 => [123,234]
  @GetMapping("ex02list")
  public void ex02list(@RequestParam("ids") ArrayList<String> ids) {
    log.info(ids);
  }

  // sample/ex02array?ids=asd&ids=sdf => [asd, sdf]
  @GetMapping("ex02array")
  public void ex02array(@RequestParam("ids") String[] ids) {
    log.info(Arrays.toString(ids));
  }

  // @RequestParam은 단일 파라미터 바인딩에 사용되며, 복잡한 객체를 바인딩할 수 없음
  // @ModelAttribute는 복합 객체 바인딩에 좋음(주로 form data나 post요청)
  // @RequestBody는 json형태의 객체를 받을 때 쓰임 HTTP 요청 본문(body)

  // sample/ex02bean?list%5B0%5D.name=aaa&list%5B0%5D.age=20&list%5B1%5D.name=bbb&list%5B1%5D.age=25 => SampleDTOList(list=[SampleDTO(name=aaa, age=20), SampleDTO(name=bbb, age=25)])
  @GetMapping("ex02bean")
  public void ex02bean(@ModelAttribute SampleDTOList list) {
    log.info(list);
  }

  // java.util.Date 타입과 사용자가 보낸 타입이 불일치함 -> @InitBinder이나 DTO 인스턴스 변수에 @DateTimeFormat(pattern = "yyyy-MM-dd")
  // sample/ex03?title=test&dueDate=2025-01-08 => TodoDTO(title=test, dueDate=Wed Jan 08 00:00:00 KST 2025)
 
  // @InitBinder
  // public void InitBinder(WebDataBinder binder) {
  //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  //   binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
  // }

  @GetMapping("ex03")
  public void ex03(TodoDTO dto) {
    log.info(dto);
  }
  

  // sample/ex04?name=aaa&age=11&page=2 => SampleDTO: SampleDTO(name=aaa, age=11) Page: 2
  @GetMapping("ex04")
  public void ex04(SampleDTO dto, @RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
    log.info("DTO: " + dto);
    log.info("Page: " + page);
    
    // 모델에 데이터 추가
    model.addAttribute("dto", dto);
    model.addAttribute("page", page);
  }


  // sample/ex06 => { "name": "길동", "age": 10 }
  @GetMapping("ex06")
  public @ResponseBody SampleDTO ex06() {
    SampleDTO dto = new SampleDTO();
    dto.setAge(10);
    dto.setName("길동");
    return dto;
  }
  
  @GetMapping("ex07")
  public ResponseEntity<String> ex07() {
    log.info("/ex07..............");
    String msg = "{\"name\": \"홍길동\"}";
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json; charset=UTF-8");

    return new ResponseEntity<>(msg, headers, HttpStatus.OK);
  }
  
  
  
  
}
