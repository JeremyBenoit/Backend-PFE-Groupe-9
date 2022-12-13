package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.user.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
public class AuthenticationController {

  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/connect")
  String connect(@RequestBody InsecureUser insecureUser) {
    String token = service.connect(insecureUser);
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    return token;
  }

  @PostMapping("/register")
  public String register(@RequestBody InsecureUser insecureUser) {
    if (insecureUser == null || insecureUser.getPseudo() == null
        || insecureUser.getPassword() == null
        || insecureUser.getPseudo().isBlank() || insecureUser.getPassword().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    String token = service.register(insecureUser);
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return token;
  }
}
