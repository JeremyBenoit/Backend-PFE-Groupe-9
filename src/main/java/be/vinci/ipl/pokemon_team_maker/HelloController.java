package be.vinci.ipl.pokemon_team_maker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String sendHelloWorld(){
    return "Hello World !";
  }
}
