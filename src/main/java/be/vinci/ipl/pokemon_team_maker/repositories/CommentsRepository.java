package be.vinci.ipl.pokemon_team_maker.repositories;

import be.vinci.ipl.pokemon_team_maker.models.comment.Comment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comment, Long> {


  List<Comment> readAllByTeamId(int teamId);
}
