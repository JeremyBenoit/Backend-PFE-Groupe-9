package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.team.NewTeam;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class TeamController {

  private final TeamsService teamsService;
  private final AuthenticationService authenticationService;

  public TeamController(TeamsService teamsService, AuthenticationService authenticationService) {
    this.teamsService = teamsService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/")
  Team createOne(@RequestBody NewTeam team, @RequestHeader("Authorization") String token) {
    if (team.getName() == null || team.getName().isBlank()
        || team.getPokemons() == null) {
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
        || team.getPokemons() == null) {
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
  Team getOne(@PathVariable Long id) {
    return teamsService.getOneById(id);
  }
  @GetMapping("/likes/users/{userId}")
  Iterable<Team> getAllByLikeUserId(@PathVariable String userId,
      @RequestHeader("Authorization") String token) {
    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(userId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    return teamsService.getAllByLikeUserId(userId);
  }

  @GetMapping("/authors/{authorId}")
  Iterable<Team> getAllByAuthorId(@PathVariable String authorId,
      @RequestHeader("Authorization") String token) {
    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(authorId)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    return teamsService.getAllByAuthorId(authorId);
  }


}
