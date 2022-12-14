package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.collection.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    Iterable<Collection> getAllByUserId(String userId);
}
