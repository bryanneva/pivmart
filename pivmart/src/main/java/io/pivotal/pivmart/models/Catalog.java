package io.pivotal.pivmart.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Catalog {
    private final UUID id;
    private final String catalogKey;
    private final String displayName;

    @JsonCreator
    public Catalog(
            @JsonProperty("id") UUID id,
            @JsonProperty("catalogKey") String catalogKey,
            @JsonProperty("displayName") String displayName
    ) {
        this.id = id;
        this.catalogKey = catalogKey;
        this.displayName = displayName;
    }
}
