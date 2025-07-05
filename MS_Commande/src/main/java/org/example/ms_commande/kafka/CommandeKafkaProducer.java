package org.example.ms_commande.kafka;



import org.example.ms_commande.dto.CommandeDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommandeKafkaProducer {
    private final KafkaTemplate<String, CommandeDTO> kafkaTemplate;

    public CommandeKafkaProducer(KafkaTemplate<String, CommandeDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCommande(CommandeDTO commande) {
        kafkaTemplate.send("commande-object-topic", commande);
        System.out.println("📤 Commande envoyée : " + commande);
    }
}
