package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.team.NewTeam;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.repositories.TeamsRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final TeamsRepository teamsRepository;

  public TeamService(TeamsRepository teamsRepository) {
    this.teamsRepository = teamsRepository;
  }

  public Iterable<Team> getAll() {
    return teamsRepository.findAll();
  }

  public Team createOne(NewTeam team) {
    return teamsRepository.save(team.toTeams());
  }

  public void deleteOne(long id) {
    teamsRepository.deleteById(id);
  }

  public Team modifyOne(long id, Team newTeam) {
    Team team = teamsRepository.findById(id).orElse(null);
    if (team == null) {
      return null;
    }
    return teamsRepository.save(newTeam);
  }

  public Team getOneByid(long id) {
    return teamsRepository.findById(id).orElse(null);
  }

  public Team getOneByName(String name) {
    return teamsRepository.findByName(name).orElse(null);
  }
}
