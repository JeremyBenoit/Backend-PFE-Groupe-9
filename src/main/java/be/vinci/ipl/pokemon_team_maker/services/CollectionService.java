package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.collection.NewCollection;
import be.vinci.ipl.pokemon_team_maker.models.collection.Collection;
import be.vinci.ipl.pokemon_team_maker.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Iterable<Collection> getAll() {
        return collectionRepository.findAll();
    }

    public Collection getOneById(long id) {
        return collectionRepository.findById(id).orElse(null);
    }

    public Collection createOne(NewCollection newCollection) {
        return collectionRepository.save(newCollection.toCollection());
    }

    public Iterable<Collection> getAllByUserId(String userId) {
        return collectionRepository.getAllByUserId(userId);
    }

}
