package be.vinci.ipl.pokemon_team_maker.models.user;

import java.util.ArrayList;
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
public class InsecureUser {

  private String pseudo;
  private String password;

  public User toUser(String hashedPassword) {
    return new User(pseudo, hashedPassword);
  }
}