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

    public Page<Profile> findPaginatedProfiles(Pageable pageable) {
        int size = pageable.getPageSize();
        int page = pageable.getPageNumber();
        int startItem = page * size;
        //List<Profile> paginatedProfiles = profileRepository.

        return null;
    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        profileRepository.findAll(pageable).forEach(profile -> profiles.add(profile));
        return profiles;
    }

    public Page<Profile> getProfilesPaginated(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }
}
