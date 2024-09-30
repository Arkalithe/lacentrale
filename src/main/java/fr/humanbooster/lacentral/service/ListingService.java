package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.entity.*;
import fr.humanbooster.lacentral.repository.ListingRepository;
import fr.humanbooster.lacentral.dto.ListingDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class ListingService implements ServiceListInterface<Listing, String, ListingDto, ListingDto> {

    private final ListingRepository listingRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final ModelService modelService;
    private final FuelService fuelService;

    @Override
    public Listing create(ListingDto listingDto) {
        return listingRepository.saveAndFlush(objectFromDto(new Listing(), listingDto));
    }

    @Override
    public Listing update(ListingDto listingDto, String id) {
        Listing listing = objectFromDto(findById(id), listingDto);
        listing.setUuid(id);
        listingRepository.flush();
        return listing;
    }

    @Override
    public Boolean delete(String id) {
        listingRepository.deleteById(id);
        return null;
    }

    @Override
    public Listing findById(String id) {
        return listingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Listing> list() {
        return listingRepository.findAll();
    }

    private Listing objectFromDto(Listing listing, ListingDto listingDto) {
        Address address = addressService.findById(listingDto.getAddress_id());
        Model model = modelService.findById(listingDto.getModel_id());
        Fuel fuel = fuelService.findById(listingDto.getFuel_id());
        User owner = userService.findById(listingDto.getOwner_id());
        listing.setCreatedAt(listingDto.getCreatedAt());
        listing.setDescription(listingDto.getDescription());
        listing.setPrice(listingDto.getPrice());
        listing.setMileage(listingDto.getMileage());
        listing.setProducedAt(listingDto.getProducedAt());
        listing.setTitle(listingDto.getTitle());
        listing.setOwner(owner);
        listing.setModel(model);
        listing.setFuel(fuel);
        listing.setAddress(address);
        return listing;
    }
}
