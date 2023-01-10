package com.techbank.account.query.infrastructure.consumers;

import com.techbank.account.common.events.AccountClosedEvent;
import com.techbank.account.common.events.AccountOpenedEvent;
import com.techbank.account.common.events.FundDepositEvent;
import com.techbank.account.common.events.FundsWithDrawEvent;
import com.techbank.account.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer{

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics="AccountOpenedEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountOpenedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();  // that will commit offset to kafka
    }

    @KafkaListener(topics="FundDepositEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundDepositEvent event, Acknowledgment ack) {

        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics="FundsWithDrawEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundsWithDrawEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics="AccountClosedEvent",groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountClosedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
