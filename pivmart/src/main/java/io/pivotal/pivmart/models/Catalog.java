package io.pivotal.pivmart.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {
    private UUID id;
    private String catalogKey;
    private String displayName;
}
