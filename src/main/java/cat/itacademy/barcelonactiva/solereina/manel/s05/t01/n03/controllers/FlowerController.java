package cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    private FlowerService service;
    @PostMapping("/add")
    public ResponseEntity<Mono<FlowerDTO>> addFlower(@RequestBody FlowerDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Mono<FlowerDTO>> updateFlower(@RequestBody FlowerDTO dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<FlowerDTO>> deleteFlowerById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Mono<FlowerDTO>> getFlowerById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Flux<FlowerDTO>> getAllFlowers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}