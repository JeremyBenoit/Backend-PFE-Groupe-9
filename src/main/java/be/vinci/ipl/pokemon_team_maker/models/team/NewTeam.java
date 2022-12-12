package be.vinci.ipl.pokemon_team_maker.models.team;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewTeam {

  private String name;
  private String creatorId;
  private List<Long> pokemons;
  private List<String> weakness;

  public Team toTeams() {
    return new Team(0L, name, creatorId, pokemons, weakness, new ArrayList<>());
  }
}
