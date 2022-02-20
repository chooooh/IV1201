package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Competence;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.repository.ProfileRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the profile service class, which defines profile relevant methods
 * that will be used by the controller classes.
 *
 * This service class is transactional, methods commits or rollbacks when returned. A new transaction is started
 * regardless of any existing transaction.
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
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

    /**
     * Methods adds the specified profile to the database.
     * @param profile, the profile to be added.
     */
    public void createProfile(Profile profile) {
        //Save the new profile
        profileRepository.save(profile);
    }

    /**
     * Method that retrieves all profiles associated with the specified person
     * @param person the person's profiles
     */
    public List<Profile> getProfilesByPerson(Person person) {
        return profileRepository.findProfileByPerson(person);
    }
}