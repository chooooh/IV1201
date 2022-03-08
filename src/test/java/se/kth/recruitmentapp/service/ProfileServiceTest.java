package se.kth.recruitmentapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.kth.recruitmentapp.domain.models.Profile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.CoreMatchers.hasItem;

@EnableAutoConfiguration
class ProfileServiceTest {
    @Autowired
    private ProfileService profileService;

    @Test
    void getProfiles() {
    }

    @Test
    void getProfilePages() {
        Pageable pageable = PageRequest.of(1, 10);
        PageImpl<Profile> profilesPageImpl = profileService.getProfilePages(pageable);
        List<Profile> profiles = profilesPageImpl.toList();
        assertThat(profiles.get(0), hasProperty("person"));
        assertThat(profiles.get(0), hasProperty("competence"));
        assertThat(profiles.get(0), hasProperty("yoe"));
    }

    @Test
    void createProfile() {
        Profile profile = new Profile();
    }

    @Test
    void getProfilesByPerson() {
    }

    @Test
    void removeProfile() {
    }
}