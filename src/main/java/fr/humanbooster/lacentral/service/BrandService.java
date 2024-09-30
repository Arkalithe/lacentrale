package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.repository.BrandRepository;
import fr.humanbooster.lacentral.entity.Brand;
import fr.humanbooster.lacentral.dto.BrandDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService implements ServiceListInterface<Brand, Long, BrandDto, BrandDto> {

    private final BrandRepository brandRepository;

    @Override
    public Brand create(BrandDto brandDto) {
        return brandRepository.saveAndFlush(objectFromDto(new Brand(), brandDto));
    }

    @Override
    public Brand update(BrandDto brandDto, Long id) {
        Brand brand = objectFromDto(findById(id), brandDto);
        brand.setId(id);
        brandRepository.flush();
        return brand;
    }

    @Override
    public Boolean delete(Long id) {
        brandRepository.deleteById(id);
        return null;
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }

    private Brand objectFromDto(Brand brand, BrandDto brandDto) {
        brand.setName(brandDto.getName());
        return brand;
    }
}
