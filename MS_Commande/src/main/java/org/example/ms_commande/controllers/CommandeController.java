package org.example.ms_commande.controllers;

import org.example.ms_commande.dto.CommandeDTO;
import org.example.ms_commande.entities.Commande;
import org.example.ms_commande.kafka.CommandeKafkaProducer;
import org.example.ms_commande.kafka.StringKafkaProducer;
import org.example.ms_commande.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandes")
@CrossOrigin("*")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeKafkaProducer commandeKafkaProducer;

    @Autowired
    private StringKafkaProducer stringKafkaProducer;

    // ✅ Obtenir toutes les commandes
    @GetMapping
    public List<CommandeDTO> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    // ✅ Obtenir une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id) {
        try {
            CommandeDTO commandeDTO = commandeService.getCommandeById(id);
            return ResponseEntity.ok(commandeDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Créer une commande + envoyer via Kafka (objet)
    @PostMapping
    public ResponseEntity<CommandeDTO> createCommande(@RequestBody CommandeDTO commandeDTO) {
        CommandeDTO created = commandeService.createCommande(commandeDTO);

        // 🔁 Envoi Kafka (objet)
        commandeKafkaProducer.sendCommande(created);

        // 🔁 Envoi Kafka (string info facultative)
        stringKafkaProducer.sendText("Nouvelle commande créée avec ID : " + created.getId());

        return ResponseEntity.ok(created);
    }

    // ✅ Modifier une commande
    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        try {
            CommandeDTO updated = commandeService.updateCommande(id, commandeDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        try {
            commandeService.deleteCommande(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Récupérer les commandes par ID client (Feign Client dans MS_Client utilise ceci)
    @GetMapping("/by-client/{clientId}")
    public List<Commande> getCommandesByClientId(@PathVariable String clientId) {
        return commandeService.getCommandesByClientId(clientId);
    }
}
