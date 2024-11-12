package aubergine.dance.service;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aubergine.init")
public record ProblemInitDirectories(List<String> directories) {
}
