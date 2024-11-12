package aubergine.dance.service.s3;

import aubergine.dance.service.StorageManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@EnableConfigurationProperties(AwsS3Properties.class)
@RequiredArgsConstructor
@Slf4j
public class S3StorageManager implements StorageManager {

    private final S3Client s3Client;
    private final AwsS3Properties awsS3Properties;

    @Override
    public void createDirectory(String directory) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(awsS3Properties.bucketName())
                .key(directory)
                .build();
        try {
            s3Client.putObject(request, RequestBody.empty());
        } catch (SdkException e) {
            log.warn("Failed to create directory: {}", directory);
        }
    }

    @Override
    public boolean isDirectoryExists(String directory) {
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(awsS3Properties.bucketName())
                .prefix(directory)
                .build();
        ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsV2Request);
        return response.hasContents();
    }
}
