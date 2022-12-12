package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.collection.NewPokemonOfUser;
import be.vinci.ipl.pokemon_team_maker.models.collection.PokemonOfUser;
import be.vinci.ipl.pokemon_team_maker.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Iterable<PokemonOfUser> getAll() {
        return collectionRepository.findAll();
    }

    public PokemonOfUser getOneById(long id) {
        return collectionRepository.findById(id).orElse(null);
    }

    public PokemonOfUser createOne(NewPokemonOfUser newPokemonOfUser) {
        return collectionRepository.save(newPokemonOfUser.toPokemonOfUser());
    }

}
