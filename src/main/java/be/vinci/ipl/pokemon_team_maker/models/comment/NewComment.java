package be.vinci.ipl.pokemon_team_maker.models.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewComment {

  private long id;
  private String user;
  private long teamId;

  private String content;

  public Comment toComment() {
    return new Comment(0L, user, teamId, content);
  }
}
