package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.entity.Brand;
import fr.humanbooster.lacentral.repository.ModelRepository;
import fr.humanbooster.lacentral.entity.Model;
import fr.humanbooster.lacentral.dto.ModelDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService implements ServiceListInterface<Model, Long, ModelDto, ModelDto> {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    @Override
    public Model create(ModelDto modelDto) {
        return modelRepository.saveAndFlush(objectFromDto(new Model(), modelDto));
    }

    @Override
    public Model update(ModelDto modelDto, Long id) {
        Model model = objectFromDto(findById(id), modelDto);
        model.setId(id);
        modelRepository.flush();
        return model;
    }

    @Override
    public Boolean delete(Long id) {
        modelRepository.deleteById(id);
        return null;
    }

    @Override
    public Model findById(Long id) {
        return modelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Model> list() {
        return modelRepository.findAll();
    }

    private Model objectFromDto(Model model, ModelDto modelDto) {
        Brand brand = brandService.findById(modelDto.getBrand_id());
        model.setName(modelDto.getName());
        model.setBrand(brand);
        return model;
    }
}
