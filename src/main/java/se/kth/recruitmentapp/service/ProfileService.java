package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.repository.ProfileRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the profile service class, which defines profile relevant methods
 * that will be used by the controller classes.
 *
 * This service class is transactional, methods commits or rollbacks when returned.
 */
@Transactional(rollbackOn = Exception.class)
@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Method that gets retrieves profiles. The user may query the number of profiles and which page to retrieve.
     * @param page Which page to fetch
     * @param size Number of profiles to fetch
     * @return specified profiles
     */
    public List<Profile> getProfiles(int page, int size) {
        List<Profile> profiles = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);
        profileRepository.findAll(pageable).forEach(profile -> profiles.add(profile));
        return profiles;
    }
}
