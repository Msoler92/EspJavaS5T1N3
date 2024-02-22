package cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.exceptions.NotFoundException;
import org.springframework.http.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerService {

    private final WebClient client = WebClient.create("http://localhost:9001");

    public Mono<FlowerDTO> save(FlowerDTO dto) {
        return client.post()
                .uri("/flower/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    public Mono<FlowerDTO> update(FlowerDTO dto) {
        return client.put()
                .uri("/flower/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), FlowerDTO.class)
                .retrieve()
                .bodyToMono(FlowerDTO.class);
    }

    public Mono<FlowerDTO> findById(Integer id) {
        /*
        ResponseEntity<FlowerDTO> response = client.get()
                .uri("/flower/getOne/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, clientResponse -> Mono.empty())
                .toEntity(FlowerDTO.class)
                .block();
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Id not found");
        }

         */
        return client.get()
                .uri("/flower/getOne/{id}", id)
                .retrieve()
                .bodyToMono(FlowerDTO.class)
                .doOnError((throwable) -> {
                    throw new NotFoundException();
                });
    }

    public Flux<FlowerDTO> findAll() {
        return client
                .get().uri("/flower/getAll")
                .retrieve()
                .bodyToFlux(FlowerDTO.class);
    }

    public void deleteById(Integer id) {
        client.delete()
                .uri("/flower/delete/{id}", id)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, clientResponse -> Mono.empty())
                .bodyToMono(FlowerDTO.class).block();
    }
}
