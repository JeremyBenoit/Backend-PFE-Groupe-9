package be.vinci.ipl.pokemon_team_maker.services;

import be.vinci.ipl.pokemon_team_maker.models.user.InsecureUser;
import be.vinci.ipl.pokemon_team_maker.models.user.User;
import be.vinci.ipl.pokemon_team_maker.properties.AuthenticationProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

  public String connect(InsecureUser insecureUser) {
    User user = usersService.getOneById(insecureUser.getPseudo());
    if (user == null) {
      return null;
    }
    if (!BCrypt.checkpw(insecureUser.getPassword(), user.getHashedPassword())) {
      return null;
    }
    return JWT.create().withIssuer("auth0").withClaim("pseudo", user.getPseudo())
        .sign(jwtAlgorithm);
  }

  public String verify(String token) {
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    try {
      String pseudo = jwtVerifier.verify(token).getClaim("pseudo").asString();
      if (!usersService.existsById(pseudo)) {
        return null;
      }
      return pseudo;
    } catch (JWTVerificationException e) {
      return null;
    }
  }

  public String register(InsecureUser insecureUser) {
    if (usersService.createOne(insecureUser) == null) {
      return null;
    }
    return JWT.create().withIssuer("auth0").withClaim("pseudo", insecureUser.getPseudo())
        .sign(jwtAlgorithm);
  }
}
