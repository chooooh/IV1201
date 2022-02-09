package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
     * Method that retrieves profiles. The user may query the number of profiles and which page to retrieve.
     * @param pageable the pageable object that determines profiles to fetch
     * @return specified profiles
     */
    public List<Profile> getProfiles(Pageable pageable) {
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findAll(pageable).forEach(profile -> profiles.add(profile));
        return profiles;
    }

    /**
     * Method that retrieves profiles of PageImpl. The user may query the number of profiles and which page to retrieve.
     * @param pageable the pageable object that determines profiles to fetch
     * @return specified profiles
     */
    public PageImpl<Profile> getProfilePages(Pageable pageable) {
        PageImpl<Profile> profiles = profileRepository.findAll(pageable);
        return profiles;
    }

}