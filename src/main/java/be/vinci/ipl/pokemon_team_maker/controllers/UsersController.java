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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {

  private final UsersService usersService;
  private final AuthenticationService authenticationService;
  private final TeamsService teamsService;
  private final LikesService likesService;

  public UsersController(UsersService usersService, AuthenticationService authenticationService,
      TeamsService teamsService, LikesService likesService) {
    this.usersService = usersService;
    this.authenticationService = authenticationService;
    this.teamsService = teamsService;
    this.likesService = likesService;
  }


  @GetMapping("/users/{id}/with-liked-and-created-teams")
  UserWithLikedAndCreatedTeams getOneWithLikedAndCreatedTeams(@PathVariable String id, @RequestHeader("Authorization") String token) {
    String userPseudo = authenticationService.verify(token);
    if(userPseudo == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    if (!userPseudo.equals(id)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    User user = usersService.getOneById(id);
    Iterable<Team> likedTeams = teamsService.getAllByIds(likesService.getAllTeamIdByAuthorId(id));
    Iterable<Team> createdTeams = teamsService.getAllByAuthorId(id);
    return new UserWithLikedAndCreatedTeams(user.getPseudo(),likedTeams,createdTeams);
  }
}
