package aubergine.dance.service.local;

import aubergine.dance.service.StorageManager;
import java.io.File;

public class LocalFileStorageManager implements StorageManager {

    @Override
    public void createDirectory(String directory) {
        if (isDirectoryExists(directory)) {
            return;
        }
        File file = new File(directory);
        file.mkdirs();
    }

    @Override
    public boolean isDirectoryExists(String directory) {
        File file = new File(directory);
        return file.exists();
    }
}
