package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.ModelDto;
import fr.humanbooster.lacentral.service.ModelService;
import fr.humanbooster.lacentral.jsonviews.ModelJsonview;
import fr.humanbooster.lacentral.entity.Model;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping
    @JsonView(ModelJsonview.modelListViewSimple.class)
    public List<Model> getAllModel() {
        return modelService.list();
    }

    @GetMapping("/{id}")
    @JsonView(ModelJsonview.modelShowView.class)
    public Model getModelById(@PathVariable Long id) {
        return modelService.findById(id);
    }

    @PostMapping
    public Model create(@Valid @RequestBody ModelDto modelDto) {
        return modelService.create(modelDto);
    }

    @PutMapping("/{id}")
    public Model update(@PathVariable Long id,@Valid @RequestBody ModelDto modelDto) {
        return modelService.update(modelDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        modelService.delete(id);
    }
}
