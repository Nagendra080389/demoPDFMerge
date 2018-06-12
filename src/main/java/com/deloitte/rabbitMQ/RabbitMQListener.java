package com.deloitte.rabbitMQ;

import com.deloitte.broker.BigOpertaion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListener.class);

    public void receiveMessage(BigOpertaion bigOpertaion){
        LOGGER.info(bigOpertaion.getAccessToken());
    }
}
