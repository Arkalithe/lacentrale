package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.AddressDto;
import fr.humanbooster.lacentral.service.AddressService;
import fr.humanbooster.lacentral.jsonviews.AddressJsonview;
import fr.humanbooster.lacentral.entity.Address;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresss")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @JsonView(AddressJsonview.addressListViewSimple.class)
    public List<Address> getAllAddress() {
        return addressService.list();
    }

    @GetMapping("/{id}")
    @JsonView(AddressJsonview.addressShowView.class)
    public Address getAddressById(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PostMapping
    public Address create(@Valid @RequestBody AddressDto addressDto) {
        return addressService.create(addressDto);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id,@Valid @RequestBody AddressDto addressDto) {
        return addressService.update(addressDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.delete(id);
    }
}
