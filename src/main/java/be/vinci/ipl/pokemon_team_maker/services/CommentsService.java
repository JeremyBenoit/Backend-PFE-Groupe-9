package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.comment.Comment;
import be.vinci.ipl.pokemon_team_maker.models.comment.NewComment;
import be.vinci.ipl.pokemon_team_maker.repositories.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

  private final CommentsRepository repository;

  public CommentsService(CommentsRepository repository) {
    this.repository = repository;
  }

  public Comment createOne(NewComment newComment) {
    return repository.save(newComment.toComment());
  }

  public Iterable<Comment> getAllByTeamId(int teamId) {
    return repository.findAllByTeamId(teamId);
  }
}