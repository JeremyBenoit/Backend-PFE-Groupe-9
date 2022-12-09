package be.vinci.ipl.pokemon_team_maker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String sendHelloWorld(){
    return "Hello World !";
  }
}
