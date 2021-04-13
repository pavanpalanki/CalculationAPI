package com.calculate.sqsconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SqsConfig {

	// Value is populated by the region code.
	@Value("${cloud.aws.region.static}")
	private String region;

	// Value is populated with the aws access key.
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;

	// Value is populated with the aws secret key
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;
	
	@Bean
	@Primary
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}
}
