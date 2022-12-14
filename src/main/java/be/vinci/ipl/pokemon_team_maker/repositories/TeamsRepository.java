package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends CrudRepository<Team, Long> {

  Optional<Team> findByName(String name);

  Iterable<Team> findAllByCreatorId(String creatorId);
  @Query( "SELECT te FROM teams te join te.likes lk WHERE lk.userId = :userId")
  Iterable<Team> findAllByLikesUserId(String userId);
}
