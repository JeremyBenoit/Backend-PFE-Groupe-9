package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.collection.NewCollection;
import be.vinci.ipl.pokemon_team_maker.models.collection.Collection;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins="http://127.0.0.1:5173")
@RequestMapping("/collection")
public class CollectionController {

    private final CollectionService collectionService;
    private final AuthenticationService authenticationService;

    public CollectionController(CollectionService collectionService, AuthenticationService authenticationService) {
        this.collectionService = collectionService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/")
    Collection createOne(@RequestBody NewCollection newCollection) {
        if (newCollection.getPokemonId() == 0 || newCollection.getUserId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return collectionService.createOne(newCollection);
    }

    @GetMapping("/")
    Iterable<Collection> getAllPokemonOfUser() {
        return collectionService.findAll();
    }

    @GetMapping("/user/{userId}")
    Iterable<Collection> getAllByUserId(@PathVariable String userId){
        return collectionService.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    Collection getOne(@PathVariable long id) {
        return collectionService.getOneById(id);
    }

}
