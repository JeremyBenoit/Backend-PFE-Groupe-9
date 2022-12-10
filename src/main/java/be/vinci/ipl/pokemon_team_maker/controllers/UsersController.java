package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.services.UsersService;
import be.vinci.ipl.pokemon_team_maker.models.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {
  private final UsersService service;

  public UsersController(UsersService service) {
    this.service = service;
  }

  @PostMapping("/users")
  public User createOne(@RequestBody InsecureUser insecureUser) {
    if (insecureUser == null || insecureUser.getPseudo() == null || insecureUser.getPassword() == null
    || insecureUser.getPseudo().isBlank() || insecureUser.getPassword().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    User createdUser = service.createOne(insecureUser);
    if (createdUser == null) throw new ResponseStatusException(HttpStatus.CONFLICT);
    return createdUser;
  }

}
