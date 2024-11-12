package aubergine.dance.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@EnableConfigurationProperties(AwsS3CredentialProperties.class)
@RequiredArgsConstructor
public class AwsS3Config {

    private final AwsS3CredentialProperties awsS3CredentialProperties;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                awsS3CredentialProperties.accessKey(),
                awsS3CredentialProperties.secretKey()
        );
        return S3Client.builder()
                .region(Region.of(awsS3CredentialProperties.region()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
