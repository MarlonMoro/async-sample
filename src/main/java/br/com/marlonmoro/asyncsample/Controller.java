package br.com.marlonmoro.asyncsample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

  @GetMapping(path = "/sync")
  public ResponseEntity<?> syncExecution(){
    return ResponseEntity.ok().build();
  }

  @GetMapping(path = "/async")
  public ResponseEntity<?> asyncExecution(){
    return ResponseEntity.ok().build();
  }

}
