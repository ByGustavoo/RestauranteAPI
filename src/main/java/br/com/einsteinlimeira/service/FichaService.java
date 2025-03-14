package br.com.einsteinlimeira.service;

import br.com.einsteinlimeira.model.dto.FichaDTO;
import br.com.einsteinlimeira.model.entity.Ficha;
import br.com.einsteinlimeira.repository.FichaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    public List<FichaDTO> findAllTickets() {
        return fichaRepository.findAll().stream().map(FichaDTO::new).toList();
    }

    public FichaDTO findTicketById(Integer id) {
        Ficha findTicket = fichaRepository.getReferenceById(id);
        return new FichaDTO(findTicket);
    }

    public FichaDTO saveTicket(FichaDTO fichaDTO) {
        return new FichaDTO(fichaRepository.save(fichaDTO.toEntity()));
    }

    public FichaDTO updateTicket(Integer id, FichaDTO fichaDTO) {
        Ficha ficha = fichaRepository.getReferenceById(id);

        Ficha newFicha = fichaDTO.toEntity();
        BeanUtils.copyProperties(newFicha, ficha, "id");

        return new FichaDTO(fichaRepository.save(fichaDTO.toEntity()));
    }

    public FichaDTO deleteTicket(Integer id) {
        Ficha ficha = fichaRepository.getReferenceById(id);
        fichaRepository.delete(ficha);
        return new FichaDTO(ficha);
    }
}