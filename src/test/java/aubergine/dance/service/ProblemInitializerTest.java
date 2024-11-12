package aubergine.dance.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class ProblemInitializerTest {

    private final StorageManager alreadyExistsStorageManager = new StorageManager() {
        @Override
        public void createDirectory(String directory) {
            // no-op
        }

        @Override
        public boolean isDirectoryExists(String directory) {
            return true;
        }
    };

    @Test
    void 디렉토리가_이미_존재하는_경우_예외를_발생한다() {
        ProblemInitDirectories directories = new ProblemInitDirectories(List.of("input"));
        ProblemInitializer problemInitializer = new ProblemInitializer(alreadyExistsStorageManager, directories);

        assertThatThrownBy(() -> problemInitializer.initializeProblem(1L))
                .isInstanceOf(ProblemAlreadyExistsException.class);
    }
}
