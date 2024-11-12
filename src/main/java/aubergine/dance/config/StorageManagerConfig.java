package aubergine.dance.config;

import aubergine.dance.service.StorageManager;
import aubergine.dance.service.local.LocalFileStorageManager;
import aubergine.dance.service.s3.AwsS3Properties;
import aubergine.dance.service.s3.S3StorageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@EnableConfigurationProperties(AwsS3Properties.class)
@RequiredArgsConstructor
public class StorageManagerConfig {

    private final S3Client s3Client;
    private final AwsS3Properties awsS3Properties;

    @Bean
    @Profile("local")
    public StorageManager localFileStorageManager() {
        return new LocalFileStorageManager();
    }

    @Bean
    @Profile("s3")
    public StorageManager s3StorageManager() {
        return new S3StorageManager(s3Client, awsS3Properties);
    }
}
