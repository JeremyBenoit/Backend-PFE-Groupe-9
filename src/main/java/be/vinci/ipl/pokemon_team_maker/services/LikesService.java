package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.like.Like;
import be.vinci.ipl.pokemon_team_maker.models.like.NewLike;
import be.vinci.ipl.pokemon_team_maker.repositories.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

  private final LikesRepository repository;

  public LikesService(LikesRepository repository) {
    this.repository = repository;
  }

  public Like createOne(NewLike newLike) {
    return repository.save(newLike.toLike());
  }
}