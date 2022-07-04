package de.demicon.connector.record;

import lombok.Builder;

import java.util.List;

@Builder
public record Countries(String name, List<User> users) {
    @Builder
    public record User(String name, String gender, String email) {
    }
}
