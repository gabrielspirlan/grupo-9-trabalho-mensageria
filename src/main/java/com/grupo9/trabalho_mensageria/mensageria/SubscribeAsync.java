package com.grupo9.trabalho_mensageria.mensageria;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.grupo9.trabalho_mensageria.application.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class SubscribeAsync implements CommandLineRunner {

    @Autowired
    private MessageService messageService;

    public void run(String... args) throws Exception {
        String projectId = "serjava-demo";
        String subscriptionId = "sub-grupo9";

        subscribeAsync(projectId, subscriptionId);
    }

    public void subscribeAsync(String projectId, String subscriptionId) {
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);

        MessageReceiver receiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
            String data = message.getData().toStringUtf8();
             if (messageService.processMessage(data)) {
                  consumer.ack();
             } else {
                  consumer.nack();
             }
        };

        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
            subscriber.startAsync().awaitRunning();
            System.out.println("Escutando Mensagem: " + subscriptionName.toString());
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrompida: " + e.getMessage());
            }
        } catch (Exception exception) {
            if (subscriber != null) {
                subscriber.stopAsync();
            }
        }
    }
}
