package be.vinci.ipl.pokemon_team_maker.models.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity(name = "teams")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  @Column(name = "id")
  private long id;

  @JsonProperty("name")
  @Column(name = "name")
  private String name;

  @JsonProperty("creatorId")
  @Column(name = "creatorId")
  private long creatorId;

  @JsonProperty("pokemons")
  @Column(name = "pokemons")
  @ElementCollection
  private ArrayList<Long> pokemons;

  @JsonProperty("weakness")
  @Column(name = "weakness")
  @ElementCollection
  private ArrayList<String> weakness;

  @JsonProperty("likes")
  @Column(name = "likes")
  @OneToMany
  private ArrayList<Like> likes;
}
