package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.team.NewTeam;
import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import be.vinci.ipl.pokemon_team_maker.repositories.TeamsRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

  private final TeamsRepository repository;

  public TeamsService(TeamsRepository repository) {
    this.repository = repository;
  }

  public Iterable<Team> getAll() {
    return repository.findAll();
  }

  public Team createOne(NewTeam team) {
    return repository.save(team.toTeams());
  }

  public void deleteOne(long id) {
    repository.deleteById(id);
  }

  public Team modifyOne(long id, Team newTeam) {
    Team team = repository.findById(id).orElse(null);
    if (team == null) {
      return null;
    }
    return repository.save(newTeam);
  }

  public Team getOneById(long id) {
    return repository.findById(id).orElse(null);
  }

  public Team getOneByName(String name) {
    return repository.findByName(name).orElse(null);
  }

  public Iterable<Team> getAllByIds(Iterable<Long> ids){
    return repository.findAllById(ids);
  }

  public Iterable<Team> getAllByAuthorId(String authorId) {
    return repository.findAllByCreatorId(authorId);
  }
}