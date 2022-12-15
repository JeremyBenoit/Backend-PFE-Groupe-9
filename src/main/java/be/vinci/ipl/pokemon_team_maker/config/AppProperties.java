package be.vinci.ipl.pokemon_team_maker.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "be.vinci.ipl.app")
@Getter
@Setter
public class AppProperties {

  private String crossOriginUrl;
}
