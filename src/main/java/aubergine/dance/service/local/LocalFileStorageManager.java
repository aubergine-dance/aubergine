package aubergine.dance.service.local;

import aubergine.dance.service.StorageManager;
import java.io.File;

public class LocalFileStorageManager implements StorageManager {

    @Override
    public void createDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public boolean isDirectoryExists(String directory) {
        File file = new File(directory);
        return file.exists();
    }
}
