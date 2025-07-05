package org.example.ms_client.kafka;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StringKafkaConsumer {

    @KafkaListener(topics = "commande-string-topic", groupId = "ms-client-group")
    public void listenText(String message) {
        System.out.println("📥 Message texte reçu : " + message);
    }
}
