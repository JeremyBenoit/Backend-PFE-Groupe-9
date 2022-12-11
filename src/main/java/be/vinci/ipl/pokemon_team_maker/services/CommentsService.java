package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.comment.Comment;
import be.vinci.ipl.pokemon_team_maker.models.comment.NoIdComment;
import be.vinci.ipl.pokemon_team_maker.repositories.CommentsRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

  private final CommentsRepository repository;

  public CommentsService(CommentsRepository repository) {
    this.repository = repository;
  }

  public Comment createOne(NoIdComment noIdComment) {
    return repository.save(noIdComment.toComment());
  }

  public List<Comment> readAllByTeamId(int teamId) {
    return repository.readAllByTeamId(teamId);
  }
}
