package me.xemor.shuffler.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameYaml(
        @JsonProperty String world,
        // "max_entropy" (default), "ghosh", "leontief" or "loopy_belief"
        @JsonProperty String craftingModel
) {
}
