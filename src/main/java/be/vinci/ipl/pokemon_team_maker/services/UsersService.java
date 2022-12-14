package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.user.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.models.user.User;
import be.vinci.ipl.pokemon_team_maker.repositories.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  private final UsersRepository usersRepository;

  public UsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public User getOneById(String pseudo) {
    return usersRepository.findById(pseudo).orElse(null);
  }

  public User createOne(InsecureUser insecureUser) {
    if (usersRepository.existsById(insecureUser.getPseudo())) {
      return null;
    }
    String hashedPassword = BCrypt.hashpw(insecureUser.getPassword(), BCrypt.gensalt());
    return usersRepository.save(insecureUser.toUser(hashedPassword));
  }

  public boolean existsById(String pseudo) {
    return usersRepository.existsById(pseudo);
  }
}