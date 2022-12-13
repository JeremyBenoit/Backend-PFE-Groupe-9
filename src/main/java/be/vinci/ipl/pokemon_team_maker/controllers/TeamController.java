package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.team.NewTeam;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.LikesService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/teams")
public class TeamController {

  private final TeamsService teamsService;
  private final AuthenticationService authenticationService;
  private final LikesService likesService;

  public TeamController(TeamsService teamsService, AuthenticationService authenticationService,
                        LikesService likesService) {
    this.teamsService = teamsService;
    this.authenticationService = authenticationService;
    this.likesService = likesService;
  }

  @PostMapping("/")
  Team createOne(@RequestBody NewTeam team, @RequestHeader("Authorization") String token) {
    if (team.getName() == null || team.getName().isBlank()
            || team.getPokemons() == null || team.getWeakness() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(team.getCreatorId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    return teamsService.createOne(team);
  }

  @GetMapping("/")
  Iterable<Team> getAll() {
    return teamsService.getAll();
  }

  @DeleteMapping("/{id}")
  void deleteOne(@PathVariable long id, @RequestHeader("Authorization") String token) {
    Team foundedTeam = teamsService.getOneById(id);
    if (foundedTeam == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(foundedTeam.getCreatorId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    teamsService.deleteOne(id);
  }

  @PutMapping("/{id}")
  Team modifyOne(@PathVariable long id, @RequestHeader("Authorization") String token,
                 @RequestBody Team team) {
    if (team.getName() == null || team.getName().isBlank() || team.getId() == id
            || team.getPokemons() == null || team.getWeakness() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(team.getCreatorId())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    Team modifiedTeam = teamsService.modifyOne(id, team);
    if (modifiedTeam == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return teamsService.modifyOne(id, team);
  }

  @GetMapping("/{id}")
  Team getOne(@PathVariable long id) {
    return teamsService.getOneById(id);
  }

  @GetMapping("/{name}")
  Team getOne(@PathVariable String name) {
    return teamsService.getOneByName(name);
  }

  @GetMapping("/likes/users/{userId}")
  Iterable<Team> getAllLikedByUserId(@PathVariable String userId){
    return teamsService.getAllByIds(likesService.getAllTeamIdByAuthorId(userId));
  }

  @GetMapping("/authors/{authorId}")
  Iterable<Team> getAllByAuthorId(@PathVariable String authorId){
    return teamsService.getAllByAuthorId(authorId);
  }
}