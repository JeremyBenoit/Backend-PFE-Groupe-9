package be.vinci.ipl.pokemon_team_maker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String sendHelloWorld(){
    String message = "Hello AWS Elastic Beanstalk!";
    try {
      InetAddress ip = InetAddress.getLocalHost();
      message += " From host: " + ip;
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return message;
  }
}
