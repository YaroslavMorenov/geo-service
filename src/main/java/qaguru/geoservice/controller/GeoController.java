package qaguru.geoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qaguru.geoservice.model.GeoJson;
import qaguru.geoservice.service.GeoService;

import java.util.List;

@RestController
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/countries")
    public List<GeoJson> getAllCountries() {
        return geoService.getAllCountries();
    }

    @GetMapping("/countries/{countryCode}")
    public GeoJson getCountryByCode(@PathVariable String countryCode) {
        return geoService.getCountryByCode(countryCode);
    }

    @PostMapping("/country")
    public GeoJson addCountry(@RequestBody GeoJson country) {
        return geoService.addCountry(country);
    }

    @PatchMapping("/country")
    public GeoJson updateCountryNameByCode(@PathVariable String countryCode, @RequestParam String newName) {
        return geoService.updateCountryName(countryCode, newName);
    }
}
