package qaguru.geoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import qaguru.geoservice.data.GeoEntity;

import java.util.UUID;

public class GeoJson {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_location")
    private String countryLocation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    public static GeoJson fromEntity(GeoEntity entity) {
        GeoJson geoJson = new GeoJson();
        geoJson.setId(entity.getId());
        geoJson.setCountryName(entity.getCountryName());
        geoJson.setCountryCode(entity.getCountryCode());
        geoJson.setCountryLocation(entity.getCountryLocation());
        return geoJson;
    }
}
