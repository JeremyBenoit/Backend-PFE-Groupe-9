package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.team.Like;
import be.vinci.ipl.pokemon_team_maker.repositories.LikesRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
  private final LikesRepository repository;

  public LikesService(LikesRepository repository) {
    this.repository = repository;
  }

  public Iterable<Long> getAllTeamIdByAuthorId(String author){
    return repository.findAllTeamIdByUserId(author);
  }
}
