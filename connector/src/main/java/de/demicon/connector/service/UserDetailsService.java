package de.demicon.connector.service;


import de.demicon.connector.record.Countries;
import de.demicon.connector.record.RandomUserResponse;
import de.demicon.connector.record.RandomUserResponse.RandomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final WebClient.Builder builder;

    private List<RandomUser> randomUsers = new ArrayList<>();

    public List<Countries> getUserDetails() {
        Map<String, List<RandomUser>> map = randomUsers.stream().collect(Collectors.groupingBy(o -> o.location().country()));
        List<Countries> countries = new ArrayList<>();
        map.forEach((key, value) -> {
            List<Countries.User> users = value.stream()
                    .map(randomUser ->
                            Countries.User.builder()
                                    .name(randomUser.name().first() + " " + randomUser.name().last())
                                    .gender(randomUser.gender())
                                    .email(randomUser.email())
                                    .build()).toList();
            countries.add(Countries.builder().name(key).users(users).build());
        });
        return countries;
    }

    @PostConstruct
    @Scheduled(cron = "0 */5 * * * *")
    private void fetchUserDetails() {
        builder.build().get().uri("api?results=50&inc=gender,name,location,email&noinfo")
                .retrieve().bodyToMono(RandomUserResponse.class)
                .doOnSuccess(response -> this.randomUsers = response.results().stream().map(o -> RandomUser.builder()
                                .email(o.email())
                                .gender(o.gender())
                                .name(o.name())
                                .location(o.location())
                                .build())
                        .collect(Collectors.toList())).block();
    }
}
