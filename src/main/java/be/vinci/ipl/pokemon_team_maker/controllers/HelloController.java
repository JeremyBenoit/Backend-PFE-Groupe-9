package be.vinci.ipl.pokemon_team_maker.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
public class HelloController {

  @GetMapping("/")
  public String sendHelloWorld() {
    return "Hello World !";
  }
}