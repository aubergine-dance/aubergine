package aubergine.dance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ProblemInitDirectories.class)
@RequiredArgsConstructor
@Slf4j
public class ProblemInitializer {

    private final StorageManager storageManager;
    private final ProblemInitDirectories problemInitDirectories;

    public void initializeProblem(long problemId) {
        if (storageManager.isDirectoryExists(problemId + "/")) {
            throw new ProblemAlreadyExistsException(problemId);
        }

        for (String directory : problemInitDirectories.directories()) {
            storageManager.createDirectory(problemId + "/" + directory);
        }
    }
}
