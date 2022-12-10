package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.repositories.UsersRepository;
import be.vinci.ipl.pokemon_team_maker.models.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
  private final UsersRepository repository;

  public UsersService(UsersRepository repository) {
    this.repository = repository;
  }

  public User readOneById(String pseudo) {
    return repository.findById(pseudo).orElse(null);
  }

  public User createOne(InsecureUser insecureUser) {
    if (repository.existsById(insecureUser.getPseudo())) {
      return null;
    }
    String hashedPassword = BCrypt.hashpw(insecureUser.getPassword(), BCrypt.gensalt());
    return repository.save(insecureUser.toUser(hashedPassword));
  }
}
