package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.collection.NewPokemonOfUser;
import be.vinci.ipl.pokemon_team_maker.models.collection.PokemonOfUser;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    private final CollectionService collectionService;
    private final AuthenticationService authenticationService;

    public CollectionController(CollectionService collectionService, AuthenticationService authenticationService) {
        this.collectionService = collectionService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/")
    PokemonOfUser createOne(@RequestBody NewPokemonOfUser pokemonOfUser, @RequestHeader("Authorization") String token) {
        if (pokemonOfUser.getPokemonId() == null || pokemonOfUser.getUserId().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String userPseudo = authenticationService.verify(token);
        if (!userPseudo.equals(pokemonOfUser.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return collectionService.createOne(pokemonOfUser);
    }

    @GetMapping("/")
    Iterable<PokemonOfUser> getAll() {
        return collectionService.getAll();
    }

    @GetMapping("/{id}")
    PokemonOfUser getOne(@PathVariable long id) {
        return collectionService.getOneById(id);
    }

}
