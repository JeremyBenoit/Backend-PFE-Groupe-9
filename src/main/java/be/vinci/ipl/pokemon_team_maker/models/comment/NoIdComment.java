package be.vinci.ipl.pokemon_team_maker.models.comment;

import be.vinci.ipl.pokemon_team_maker.models.comment.Comment;
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
public class NoIdComment {

  private int id;
  private String user;
  private int teamId;

  private String content;

  public Comment toComment(){
    return new Comment(0,user, teamId,content);
  }
}
