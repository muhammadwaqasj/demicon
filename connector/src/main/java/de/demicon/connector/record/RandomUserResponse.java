package de.demicon.connector.record;

import lombok.Builder;

import java.util.List;

public record RandomUserResponse(List<RandomUser> results) {
    @Builder
    public record RandomUser(String gender, Name name, String email, Location location) {

        public record Name(String title, String first, String last) {
        }

        public record Street(String number, String name) {
        }

        public record Coordinates(String latitude, String longitude) {
        }

        public record Timezone(String offset, String description) {
        }

        public record Location(Street street, String city, String state, String country, String postcode,
                               Coordinates coordinates, Timezone timezone) {
        }

    }
}

