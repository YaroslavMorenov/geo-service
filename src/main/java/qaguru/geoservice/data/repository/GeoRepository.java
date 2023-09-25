package qaguru.geoservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qaguru.geoservice.data.GeoEntity;

import java.util.UUID;

public interface GeoRepository extends JpaRepository<GeoEntity, UUID> {

    GeoEntity findCountryEntityByCountryCode(String countryCode);
}
