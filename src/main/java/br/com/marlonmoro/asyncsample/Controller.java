package br.com.marlonmoro.asyncsample;

import br.com.marlonmoro.asyncsample.dto.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

  @Autowired
  private SampleService sampleService;

  @GetMapping(path = "/sync")
  public ResponseEntity<Sample> processSync() {
    return ResponseEntity.ok(sampleService.createSyncSample());
  }

  @GetMapping(path = "/async")
  public ResponseEntity<Sample> processAsync() {
    return ResponseEntity.ok(sampleService.createAsyncSample());
  }

  @GetMapping(path = "/exception/async")
  public ResponseEntity<Sample> processExceptionAsync() {
    return ResponseEntity.ok(sampleService.createAsyncExceptionSample());
  }


}
