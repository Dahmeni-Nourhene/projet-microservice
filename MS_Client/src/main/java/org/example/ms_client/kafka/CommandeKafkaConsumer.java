package org.example.ms_client.kafka;



import org.example.ms_client.dto.CommandeDTO;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CommandeKafkaConsumer {

    @KafkaListener(topics = "commande-object-topic", groupId = "ms-client-group", containerFactory = "commandeKafkaListenerFactory")
    public void listenCommande(CommandeDTO commande) {
        System.out.println("📥 Commande reçue : " + commande);
    }
}
