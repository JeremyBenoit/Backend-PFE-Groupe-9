package be.vinci.ipl.pokemon_team_maker.controllers;

import be.vinci.ipl.pokemon_team_maker.models.comment.Comment;
import be.vinci.ipl.pokemon_team_maker.models.comment.NewComment;
import be.vinci.ipl.pokemon_team_maker.services.AuthenticationService;
import be.vinci.ipl.pokemon_team_maker.services.CommentsService;
import be.vinci.ipl.pokemon_team_maker.services.TeamsService;
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
  private final TeamsService teamsService;

  public CommentsController(CommentsService commentsService,
                            AuthenticationService authenticationService, TeamsService teamsService) {
    this.commentsService = commentsService;
    this.authenticationService = authenticationService;
    this.teamsService = teamsService;
  }

  @GetMapping("/comments/teams/{teamId}")
  public Iterable<Comment> getAllByTeamId(@PathVariable int teamId) {
    return commentsService.getAllByTeamId(teamId);
  }

  @PostMapping("/comments/")
  public Comment createOne(@RequestBody NewComment newComment,
                           @RequestHeader("Authorization") String token) {
    if (newComment == null || newComment.getUser() == null || newComment.getContent() == null
            || newComment.getContent().isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (teamsService.getOneById(newComment.getTeamId()) == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    String userPseudo = authenticationService.verify(token);
    if (!userPseudo.equals(newComment.getUser())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
    return commentsService.createOne(newComment);
  }
}