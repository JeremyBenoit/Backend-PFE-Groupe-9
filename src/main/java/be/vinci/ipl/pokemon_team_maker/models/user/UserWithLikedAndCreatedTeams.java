package be.vinci.ipl.pokemon_team_maker.models.user;

import be.vinci.ipl.pokemon_team_maker.models.team.Team;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserWithLikedAndCreatedTeams extends User{
  private String pseudo;
  private Iterable<Team> likedTeams;
  private Iterable<Team> createdTeams;
}