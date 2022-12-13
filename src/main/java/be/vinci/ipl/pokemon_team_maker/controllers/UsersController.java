package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.models.user.User;
import be.vinci.ipl.pokemon_team_maker.models.user.UserWithLikedAndCreatedTeams;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.LikesService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
import be.vinci.ipl.pokemon_team_maker.services.UsersService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService, AuthenticationService authenticationService,
                         TeamsService teamsService, LikesService likesService) {
    this.usersService = usersService;
  }
}