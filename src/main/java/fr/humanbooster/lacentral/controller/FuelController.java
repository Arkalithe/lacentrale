package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.FuelDto;
import fr.humanbooster.lacentral.service.FuelService;
import fr.humanbooster.lacentral.jsonviews.FuelJsonview;
import fr.humanbooster.lacentral.entity.Fuel;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fuels")
@AllArgsConstructor
public class FuelController {

    private final FuelService fuelService;

    @GetMapping
    @JsonView(FuelJsonview.fuelListViewSimple.class)
    public List<Fuel> getAllFuel() {
        return fuelService.list();
    }

    @GetMapping("/{id}")
    @JsonView(FuelJsonview.fuelShowView.class)
    public Fuel getFuelById(@PathVariable Long id) {
        return fuelService.findById(id);
    }

    @PostMapping
    public Fuel create(@Valid @RequestBody FuelDto fuelDto) {
        return fuelService.create(fuelDto);
    }

    @PutMapping("/{id}")
    public Fuel update(@PathVariable Long id,@Valid @RequestBody FuelDto fuelDto) {
        return fuelService.update(fuelDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fuelService.delete(id);
    }
}
