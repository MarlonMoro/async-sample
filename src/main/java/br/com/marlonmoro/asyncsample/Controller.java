package br.com.marlonmoro.asyncsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

  @Autowired
  private SyncService syncService;

  @GetMapping(path = "/sync")
  public ResponseEntity<Response> syncExecution(){
    return ResponseEntity.ok(syncService.execute());
  }

  @GetMapping(path = "/async")
  public ResponseEntity<Response> asyncExecution(){
    return ResponseEntity.ok().build();
  }

}
