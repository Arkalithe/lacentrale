package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.repository.FuelRepository;
import fr.humanbooster.lacentral.entity.Fuel;
import fr.humanbooster.lacentral.dto.FuelDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class FuelService implements ServiceListInterface<Fuel, Long, FuelDto, FuelDto> {

    private final FuelRepository fuelRepository;

    @Override
    public Fuel create(FuelDto fuelDto) {
        return fuelRepository.saveAndFlush(objectFromDto(new Fuel(), fuelDto));
    }

    @Override
    public Fuel update(FuelDto fuelDto, Long id) {
        Fuel fuel = objectFromDto(findById(id), fuelDto);
        fuel.setId(id);
        fuelRepository.flush();
        return fuel;
    }

    @Override
    public Boolean delete(Long id) {
        fuelRepository.deleteById(id);
        return null;
    }

    @Override
    public Fuel findById(Long id) {
        return fuelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Fuel> list() {
        return fuelRepository.findAll();
    }

    private Fuel objectFromDto(Fuel fuel, FuelDto fuelDto) {
        fuel.setLogo(fuelDto.getLogo());
        fuel.setType(fuelDto.getType());
        return fuel;
    }
}
