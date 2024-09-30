package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.ListingDto;
import fr.humanbooster.lacentral.service.ListingService;
import fr.humanbooster.lacentral.jsonviews.ListingJsonview;
import fr.humanbooster.lacentral.entity.Listing;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listings")
@AllArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    @JsonView(ListingJsonview.listingListViewSimple.class)
    public List<Listing> getAllListing() {
        return listingService.list();
    }

    @GetMapping("/{id}")
    @JsonView(ListingJsonview.listingShowView.class)
    public Listing getListingById(@PathVariable String id) {
        return listingService.findById(id);
    }

    @PostMapping
    public Listing create(@Valid  @RequestBody ListingDto listingDto) {
        return listingService.create(listingDto);
    }

    @PutMapping("/{id}")
    public Listing update(@PathVariable String id,@Valid @RequestBody ListingDto listingDto) {
        return listingService.update(listingDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        listingService.delete(id);
    }
}
