package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.repository.AddressRepository;
import fr.humanbooster.lacentral.entity.Address;
import fr.humanbooster.lacentral.dto.AddressDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService implements ServiceListInterface<Address, Long, AddressDto, AddressDto> {

    private final AddressRepository addressRepository;

    @Override
    public Address create(AddressDto addressDto) {
        return addressRepository.saveAndFlush(objectFromDto(new Address(), addressDto));
    }

    @Override
    public Address update(AddressDto addressDto, Long id) {
        Address address = objectFromDto(findById(id), addressDto);
        address.setId(id);
        addressRepository.flush();
        return address;
    }

    @Override
    public Boolean delete(Long id) {
        addressRepository.deleteById(id);
        return null;
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Address> list() {
        return addressRepository.findAll();
    }

    private Address objectFromDto(Address address, AddressDto addressDto) {
        address.setCity(addressDto.getCity());
        address.setStreetName(addressDto.getStreetName());
        address.setStreetNumber(addressDto.getStreetNumber());
        address.setLatitude(addressDto.getLatitude());
        address.setLongitude(addressDto.getLongitude());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }
}
