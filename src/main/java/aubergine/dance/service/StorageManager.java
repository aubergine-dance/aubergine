package aubergine.dance.service;

public interface StorageManager {

    void createDirectory(String directory);

    boolean isDirectoryExists(String directory);
}
