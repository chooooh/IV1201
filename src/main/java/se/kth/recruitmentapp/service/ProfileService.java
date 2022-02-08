package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfiles(int page, int size) {
        List<Profile> profiles = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        profileRepository.findAll(pageable).forEach(profile -> profiles.add(profile));
        return profiles;
    }
}
