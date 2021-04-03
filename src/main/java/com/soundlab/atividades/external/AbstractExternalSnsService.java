package com.soundlab.atividades.external;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundlab.atividades.exceptions.ExternalServiceException;

import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.Map;

public class AbstractExternalSnsService<T> {
    private static final Logger LOG = Logger.getLogger(AbstractExternalSnsService.class);
    private final AmazonSNS client;
    private final String topicArn;
    private final ObjectMapper mapper = new ObjectMapper();

    public AbstractExternalSnsService(AmazonSNS client, String topicArn) {
        this.client = client;
        this.topicArn = topicArn;
    }

    public void publish(T data) {

        try {
            PublishRequest request = new PublishRequest()
                .withMessage(serializeObject(data))
                .withTopicArn(topicArn)
                .withMessageAttributes(getMessageAttributeMap());

            PublishResult result = client.publish(request);

            LOG.info(String.format("Successfully placed message on SNS. (MessageId: %s | StatusCode: %s)",
                result.getMessageId(), result.getSdkHttpMetadata().getHttpStatusCode()));

        } catch (Exception e) {
            throw new ExternalServiceException("ExternalSnsService", e.getMessage());
        }
    }

    private String serializeObject(T data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Serialization error";
    }

    private static Map<String, MessageAttributeValue> getMessageAttributeMap() {
        Map<String, MessageAttributeValue> messageAttributeMap = new HashMap<>();

        MessageAttributeValue messageAttributeValue = new MessageAttributeValue()
            .withStringValue("application/json")
            .withDataType("String");

        messageAttributeMap.put("contentType", messageAttributeValue);
        return messageAttributeMap;
    }
}
