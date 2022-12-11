package be.vinci.ipl.pokemon_team_maker.controllers;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.models.user.InsecureUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthenticationController {
  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/connect")
  String connect(@RequestBody InsecureUser insecureUser) {
    String token =  service.connect(insecureUser);
    if (token == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    return token;
  }
}
