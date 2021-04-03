package com.soundlab.atividades.configurations;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;

@Configuration
public class AwsConfig {

    private static final Logger LOG = LogManager.getLogger(AwsConfig.class);

    @Bean
    public AmazonSNS snsClient() {
        Instant startTime = Instant.now();

        AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

        double duration = Duration.between(startTime, Instant.now()).toMillis();
        LOG.info("AWS SNS client initialization completed in: {} ms", String.format("%.0f", duration));
        return snsClient;
    }
}
