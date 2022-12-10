package be.vinci.ipl.pokemon_team_maker.team.data;

import be.vinci.ipl.pokemon_team_maker.team.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends CrudRepository<Team,Long> {

}
