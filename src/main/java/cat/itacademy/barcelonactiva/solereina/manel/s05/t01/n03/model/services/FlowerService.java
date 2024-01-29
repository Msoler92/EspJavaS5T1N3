package cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerService {

    WebClient flowerWebClient = WebClient.create("http://localhost:9001");

    public FlowerDTO save(FlowerDTO dto) {
        return null;
    }

    public FlowerDTO update(FlowerDTO dto) {
        return null;
    }

    public FlowerDTO findById(Integer id) {
        return null;
    }

    public List<FlowerDTO> findAll() {
        return null;
    }

    public void deleteById(Integer id) {

    }
}
