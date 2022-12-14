package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.like.Like;
import be.vinci.ipl.pokemon_team_maker.models.like.NewLike;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.LikesService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/likes")
public class LikesController {

  private final TeamsService teamsService;
  private final AuthenticationService authenticationService;
  private final LikesService likesService;

  public LikesController(TeamsService teamsService, AuthenticationService authenticationService,
      LikesService likesService) {
    this.teamsService = teamsService;
    this.authenticationService = authenticationService;
    this.likesService = likesService;
  }

  @PostMapping("/")
  Like createOne(@RequestBody NewLike newLike, @RequestHeader("Authorization") String token){
    Team team = teamsService.getOneById(newLike.getTeamId());
    if(team == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    if(team.getLikes().stream()
        .anyMatch(like -> like.getTeamId().equals(newLike.getTeamId()) && like.getUserId().equals(newLike.getUserId()))){
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(team.getCreatorId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    return likesService.createOne(newLike);
  }
}
