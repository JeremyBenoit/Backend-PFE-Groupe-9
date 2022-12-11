package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.services.TeamService;
import be.vinci.ipl.pokemon_team_maker.models.team.NewTeam;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
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

  private final TeamService service;

  public TeamController(TeamService service) {
    this.service = service;
  }

  @PostMapping("")
  void createTeam(@RequestBody NewTeam team, @RequestHeader String token) {
    if (team.getName() == null || team.getName().isBlank()
        || team.getPokemons() == null || team.getWeakness() == null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    service.createTeam(team);
  }
  @GetMapping("")
  Iterable<Team> getAllTeams(){
    return service.getAllTeams();
  }
  @DeleteMapping("/{id}")
  void deleteTeam(@PathVariable long id, @RequestHeader String token){
    service.deleteTeam(id);
  }
  @PutMapping("/{id}")
  void modifyTeam(@PathVariable long id, @RequestHeader String token, @RequestBody Team team){
    if (team.getName() == null || team.getName().isBlank()
        || team.getPokemons() == null || team.getWeakness() == null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    service.modifyTeam(id, team);
  }
  @GetMapping("/{id}")
  Team getTeam(@PathVariable long id, @RequestHeader String token){
    return service.getOneTeamByid(id);
  }
  @GetMapping("/{name}")
  Team getTeam(@PathVariable String name, @RequestHeader String token){
    return service.getOneTeamByName(name);
  }
}
