package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.collection.PokemonOfUser;
import org.springframework.data.repository.CrudRepository;

public interface CollectionRepository extends CrudRepository<PokemonOfUser, Long> {
}
