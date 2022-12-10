package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.properties.AuthenticationProperties;
import be.vinci.ipl.pokemon_team_maker.models.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UsersService usersService;
  private final Algorithm jwtAlgorithm;
  private final JWTVerifier jwtVerifier;

  public AuthenticationService(UsersService usersService, AuthenticationProperties properties) {
    this.usersService = usersService;
    this.jwtAlgorithm = Algorithm.HMAC512(properties.getSecret());
    this.jwtVerifier = JWT.require(this.jwtAlgorithm).withIssuer("auth0").build();
  }

  public String connect(InsecureUser user) {
    User credential = usersService.readOneById(user.getPseudo());
    if (credential == null) return null;
    if (!BCrypt.checkpw(user.getPassword(), credential.getHashedPassword())) return null;
    return JWT.create().withIssuer("auth0").withClaim("pseudo", credential.getPseudo()).sign(jwtAlgorithm);
  }
}
