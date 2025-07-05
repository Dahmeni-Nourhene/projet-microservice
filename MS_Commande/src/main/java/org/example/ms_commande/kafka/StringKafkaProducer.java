package org.example.ms_commande.kafka;



import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StringKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StringKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendText(String message) {
        kafkaTemplate.send("commande-string-topic", message);
        System.out.println("📤 Message texte envoyé : " + message);
    }
}
