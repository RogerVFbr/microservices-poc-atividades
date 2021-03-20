package com.soundlab.lambdahandler;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.soundlab.atividades.AtividadesApplication;
import com.soundlab.atividades.exceptions.StreamLambdaHandlerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

public class StreamLambdaHandler implements RequestStreamHandler {
    private static final Logger LOG = LogManager.getLogger(StreamLambdaHandler.class);

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        long startTime = Instant.now().minusMillis(500).toEpochMilli();

        LambdaContainerHandler
            .getContainerConfig()
            .setDefaultContentCharset(StandardCharsets.UTF_8.name());

        try {
            handler = new SpringBootProxyHandlerBuilder()
                .defaultProxy()
                .asyncInit(startTime)
                .springBootApplication(AtividadesApplication.class)
                .buildAndInitialize();

        } catch (ContainerInitializationException e) {
            throw new StreamLambdaHandlerException(e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException {
        if (isLambdaWarmupPluginCall(context)) {
            LOG.warn("Lambda warming mechanism triggered.");
            return;
        }
        Instant startTime = Instant.now();

        handler.proxyStream(inputStream, outputStream, context);

        double duration = Duration.between(startTime, Instant.now()).toMillis();
        LOG.info("Request completed in: {} ms", String.format("%.0f", duration));
    }

    private boolean isLambdaWarmupPluginCall(Context context) {
        return context.getClientContext() != null &&
            context.getClientContext().getCustom() != null &&
            context.getClientContext().getCustom().getOrDefault("source", "")
                .equals("serverless-plugin-warmup");
    }
}
