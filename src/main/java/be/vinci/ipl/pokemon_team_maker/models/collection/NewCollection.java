package be.vinci.ipl.pokemon_team_maker.models.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewCollection {
    private long pokemonId;

    private String userId;

    public Collection toCollection() {
        return new Collection(0L, pokemonId, userId);
    }
}
