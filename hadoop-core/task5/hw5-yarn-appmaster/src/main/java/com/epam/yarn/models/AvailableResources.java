package com.epam.yarn.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Anton_Solovev
 * @since 10/6/16.
 */
@Setter
@NoArgsConstructor
public class AvailableResources {

    @JsonProperty
    private Beans[] beans;

    public Beans[] getBeans() {
        return beans;
    }

    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Beans {

        @JsonProperty
        private int AvailableMB;
        @JsonProperty
        private int AvailableVCores;

        public Beans(int availableMB, int availableVCores) {
            this.AvailableMB = availableMB;
            this.AvailableVCores = availableVCores;
        }

        public int getAvailableMB() {
            return AvailableMB;
        }

        public int getAvailableVCores() {
            return AvailableVCores;
        }

    }

}

