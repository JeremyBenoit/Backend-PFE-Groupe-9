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
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;
    private final AuthenticationService authenticationService;

    public CollectionController(CollectionService collectionService, AuthenticationService authenticationService) {
        this.collectionService = collectionService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/")
    Collection createOne(@RequestBody NewCollection newCollection,  @RequestHeader("Authorization") String token) {
        if (newCollection.getPokemonId() == 0 || newCollection.getUserId() == null || newCollection.getUserId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String userPseudo = authenticationService.verify(token);
        if (!userPseudo.equals(newCollection.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return collectionService.createOne(newCollection);
    }

    @GetMapping("/")
    Iterable<Collection> getAll() {
        return collectionService.getAll();
    }

    @GetMapping("/user/{userId}")
    Iterable<Collection> getAllByUserId(@PathVariable String userId, @RequestHeader("Authorization") String token){
        String userPseudo = authenticationService.verify(token);
        if (!userPseudo.equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return collectionService.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    Collection getOne(@PathVariable long id) {
        return collectionService.getOneById(id);
    }

}
