package be.vinci.ipl.pokemon_team_maker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String sendHelloWorld() {
    return "Hello World !";
  }
}
