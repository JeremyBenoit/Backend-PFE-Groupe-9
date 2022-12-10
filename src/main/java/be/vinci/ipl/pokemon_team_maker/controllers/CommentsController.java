package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.Comment;
import be.vinci.ipl.pokemon_team_maker.models.NoIdComment;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.CommentsService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController

public class CommentsController {

  private final CommentsService commentsService;
  private final AuthenticationService authenticationService;

  public CommentsController(CommentsService commentsService,
      AuthenticationService authenticationService) {
    this.commentsService = commentsService;
    this.authenticationService = authenticationService;
  }

  @GetMapping("/comments/teams/{teamId}")
  public List<Comment> readAllByTeamId(@PathVariable int teamId) {
    return commentsService.readAllByTeamId(teamId);
  }

  @PostMapping("/comments/")
  public Comment createOne(@RequestBody NoIdComment noIdComment,
      @RequestHeader("Authorization") String token) {
    if (noIdComment == null || noIdComment.getUser() == null || noIdComment.getContent() == null
        || noIdComment.getContent().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    //TODO verifier si la team existe
    String userPseudo = authenticationService.verify(token);

    if (!userPseudo.equals(noIdComment.getUser())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    return commentsService.createOne(noIdComment);
  }
}
