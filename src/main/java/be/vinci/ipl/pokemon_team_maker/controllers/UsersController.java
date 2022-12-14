package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
import be.vinci.ipl.pokemon_team_maker.services.UsersService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService, AuthenticationService authenticationService,
      TeamsService teamsService) {
    this.usersService = usersService;
  }
}
