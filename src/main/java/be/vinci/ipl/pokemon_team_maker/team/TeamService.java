package be.vinci.ipl.pokemon_team_maker.team;

import be.vinci.ipl.pokemon_team_maker.team.data.TeamsRepository;
import be.vinci.ipl.pokemon_team_maker.team.model.NewTeam;
import be.vinci.ipl.pokemon_team_maker.team.model.Team;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService {

  private TeamsRepository teamsRepository;
  public Iterable<Team> getAllTeams() {
    return teamsRepository.findAll();
  }

  public void createTeam(NewTeam team) {
    teamsRepository.save(team.toTeams());
  }

  public void deleteTeam(long id) {
    teamsRepository.deleteById(id);
  }

  public void modifyTeam(long id, Team newTeam) {
    Optional<Team> team = teamsRepository.findById(id);
    if (team.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    teamsRepository.save(newTeam);
  }

  public Team getOneTeam(long id) {
    Optional<Team> team = teamsRepository.findById(id);
    if (team.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return team.get();
  }
}
