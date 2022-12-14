package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.like.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends CrudRepository<Like, Long> {

}
