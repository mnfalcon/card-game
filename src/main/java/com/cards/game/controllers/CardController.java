package com.cards.game.controllers;

import com.cards.game.models.Card;
import com.cards.game.models.exceptions.NotFoundResponse;
import com.cards.game.services.AmazonService;
import com.cards.game.services.CardService;
import com.cards.game.services.exceptions.ImageRequiredException;
import com.cards.game.services.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private AmazonService amazonService;

    /** To save a Card from Postman, select the 'form-data' type,
     * next to the input for the image select "media" and browse for the image.
     * Then add another field called data and it's vlue will be the whole JSON object */
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity save(@RequestParam("data") String cardData, @RequestPart(required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Card card = mapper.readValue(cardData, Card.class);
        Card newCard = new Card(card);

        if (card.getImageUrl() == null) {
            if (file != null) {
                String imgUrl = amazonService.uploadImage(file);
                newCard.setImageUrl(imgUrl);
            } else {
                throw new ImageRequiredException();
            }
        }

        return ResponseEntity.ok(cardService.save(newCard));
    }

    @PostMapping("/image") // TODO Remove this endpoint
    public ResponseEntity saveImage(@RequestPart MultipartFile file) {
        return ResponseEntity.ok(amazonService.uploadImage(file));
    }

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity getByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "limit", defaultValue = "100") Integer limit) {
        return ResponseEntity.ok(cardService.getByPage(page, limit));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Card card, @PathVariable("id") Long id) {
        return ResponseEntity.ok(cardService.update(card, id));
    }

}
