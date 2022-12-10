package be.vinci.ipl.pokemon_team_maker.team.model;

import java.util.ArrayList;
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
  private long creatorId;
  private ArrayList<Long> pokemons;
  private ArrayList<String> weakness;

  public Team toTeams() { return new Team(0L,name,creatorId,pokemons,weakness);}
}
