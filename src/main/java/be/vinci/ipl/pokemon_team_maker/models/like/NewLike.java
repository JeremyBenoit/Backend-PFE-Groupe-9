package be.vinci.ipl.pokemon_team_maker.models.like;

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
public class NewLike {

  private Long teamId;

  private String userId;

  public Like toLike(){
    return new Like(0L, teamId, userId);
  }
}
