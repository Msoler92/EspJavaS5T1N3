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
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerService {

    private WebClient client = WebClient.create("http://localhost:9001");

    public FlowerDTO save(FlowerDTO dto) {
        ResponseEntity<FlowerDTO> response = client.post()
                .uri("/flower/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto, FlowerDTO.class)
                .retrieve()
                .toEntity(FlowerDTO.class)
                .block();
        return response.getBody();
    }

    public FlowerDTO update(FlowerDTO dto) {
        return null;
    }

    public FlowerDTO findById(Integer id) {
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
        return response.getBody();
    }

    public List<FlowerDTO> findAll() {
        return null;
    }

    public void deleteById(Integer id) {

    }
}
