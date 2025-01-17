package com.tomk99.jwkisokos.controller;

import com.tomk99.jwkisokos.model.Card;
import com.tomk99.jwkisokos.model.Period;
import com.tomk99.jwkisokos.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(card);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(
            @RequestParam("name") String name,
            @RequestParam("responsible") String responsible,
            @RequestParam("lastPerformed") String lastPerformed,
            @RequestParam("period") Period period,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Convert MultipartFile to byte array
        byte[] fileData = file.getBytes();

        Card card = new Card();
        card.setName(name);
        card.setResponsible(responsible);
        card.setLastPerformed(LocalDate.parse(lastPerformed));
        card.setPeriod(period);
        card.setFileType(file.getContentType());
        card.setFileData(fileData);

        // Save card to the database
        Card savedCard = cardRepository.save(card);
        return ResponseEntity.ok(savedCard);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
