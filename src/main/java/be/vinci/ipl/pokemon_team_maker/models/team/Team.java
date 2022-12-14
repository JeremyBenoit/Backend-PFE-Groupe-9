package be.vinci.ipl.pokemon_team_maker.models.team;

import be.vinci.ipl.pokemon_team_maker.models.like.Like;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
  @Column(name = "creator_id")
  private String creatorId;

  @JsonProperty("pokemons")
  @Column(name = "pokemons")
  @ElementCollection
  private List<Long> pokemons;

  @JsonProperty("weakness")
  @Column(name = "weakness")
  @ElementCollection
  private List<String> weakness;

  @JsonProperty("likes")
  @OneToMany(mappedBy = "teamId", targetEntity = Like.class, cascade=CascadeType.ALL, orphanRemoval=true)
  private List<Like> likes;
}
