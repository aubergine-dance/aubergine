package aubergine.dance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3.credentials")
public record AwsS3CredentialProperties(String region, String accessKey, String secretKey) {
}
