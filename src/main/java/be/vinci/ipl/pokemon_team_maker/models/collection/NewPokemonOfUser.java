package be.vinci.ipl.pokemon_team_maker.models.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewPokemonOfUser {
    private Long pokemonId;

    private String userId;

    public PokemonOfUser toPokemonOfUser() {
        return new PokemonOfUser(0L, pokemonId, userId);
    }
}
