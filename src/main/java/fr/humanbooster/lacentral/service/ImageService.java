package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.repository.ImageRepository;
import fr.humanbooster.lacentral.entity.Image;
import fr.humanbooster.lacentral.dto.ImageDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService implements ServiceListInterface<Image, String, ImageDto, ImageDto> {

    private final ImageRepository imageRepository;

    @Override
    public Image create(ImageDto imageDto) {
        return imageRepository.saveAndFlush(objectFromDto(new Image(), imageDto));
    }

    @Override
    public Image update(ImageDto imageDto, String id) {
        Image image = objectFromDto(findById(id), imageDto);
        image.setUuid(id);
        imageRepository.flush();
        return image;
    }

    @Override
    public Boolean delete(String id) {
        imageRepository.deleteById(id);
        return null;
    }

    @Override
    public Image findById(String id) {
        return imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Image> list() {
        return imageRepository.findAll();
    }

    private Image objectFromDto(Image image, ImageDto imageDto) {

        image.setPath(imageDto.getPath());
        return image;
    }
}
