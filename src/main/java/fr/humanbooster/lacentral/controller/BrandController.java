package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.BrandDto;
import fr.humanbooster.lacentral.service.BrandService;
import fr.humanbooster.lacentral.jsonviews.BrandJsonview;
import fr.humanbooster.lacentral.entity.Brand;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @JsonView(BrandJsonview.brandListViewSimple.class)
    public List<Brand> getAllBrand() {
        return brandService.list();
    }

    @GetMapping("/{id}")
    @JsonView(BrandJsonview.brandShowView.class)
    public Brand getBrandById(@PathVariable Long id) {
        return brandService.findById(id);
    }

    @PostMapping
    public Brand create(@Valid  @RequestBody BrandDto brandDto) {
        return brandService.create(brandDto);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id,@Valid @RequestBody BrandDto brandDto) {
        return brandService.update(brandDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }
}
