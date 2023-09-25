package qaguru.geoservice.service;

import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import qaguru.geoservice.data.GeoEntity;
import qaguru.geoservice.data.repository.GeoRepository;
import qaguru.geoservice.model.GeoJson;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoService {

    private static final Logger LOG = LoggerFactory.getLogger(GeoService.class);
    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    public @Nonnull
    List<GeoJson> getAllCountries() {
        return geoRepository.findAll()
                .stream()
                .map(GeoJson::fromEntity)
                .collect(Collectors.toList());
    }

    public @Nonnull
    GeoJson getCountryByCode(@Nonnull String countryCode) {
        GeoEntity country = geoRepository.findCountryEntityByCountryCode(countryCode);
        if (country == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can`t find country by given code: " + countryCode);
        }
        return GeoJson.fromEntity(country);
    }

    public @Nonnull
    GeoJson addCountry(@Nonnull GeoJson country) {
        final String countryCode = country.getCountryCode();
        final String countryName = country.getCountryName();
        final String countryLocation = country.getCountryLocation();

        GeoEntity ge = new GeoEntity();
        ge.setCountryCode(countryCode);
        ge.setCountryName(countryName);
        ge.setCountryLocation(countryLocation);
        try {
            return GeoJson.fromEntity(geoRepository.save(ge));
        } catch (DataIntegrityViolationException e) {
            LOG.error("### Error while creating country: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Country with name '" + countryName + "' already exists", e);
        }
    }

    public GeoJson updateCountryName(String countryCode, String newName) {
        GeoEntity country = geoRepository.findCountryEntityByCountryCode(countryCode);
        if (country != null) {
            GeoEntity countryEntity = new GeoEntity();
            countryEntity.setCountryName(newName);
            return GeoJson.fromEntity(geoRepository.save(countryEntity));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can`t find country by given code: " + countryCode);
    }
}
