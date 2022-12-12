package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.team.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Like, Long> {

  @Query("select t.teamId from likes t where t.authorId = ?1")
  Iterable<Long> findAllTeamIdByUserId(String userId);
}
