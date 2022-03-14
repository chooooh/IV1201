package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.models.Competence;
import se.kth.recruitmentapp.domain.models.Person;
import se.kth.recruitmentapp.domain.models.Profile;
import se.kth.recruitmentapp.repository.CompetenceRepository;
import se.kth.recruitmentapp.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    @Autowired
    private CompetenceRepository competenceRepository;

    /**
     * Method that retrieves profiles of PageImpl. The user may query the number of profiles and which page to retrieve.
     * @param pageable the pageable object that determines profiles to fetch
     * @return specified profiles
     */
    public PageImpl<Profile> getProfilePages(Pageable pageable) {
        //Check selected language
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();

        PageImpl<Profile> profiles = profileRepository.findAllByCompetenceLanguageCode(pageable, language);
        return profiles;
    }

    /**
     * Methods adds the specified profile to the database.
     * @param profile, the profile to be added.
     */
    public void createProfile(Profile profile) {
        Profile pro = profileRepository.findProfileByCompetenceIdAndPersonId(profile.getCompetence().getId(), profile.getPerson().getId());
        if(pro != null){
            return;
        }

        Competence competence = profile.getCompetence();
        int competenceNameId = competence.getCompetenceNameId();
        List<Competence> competenceList = competenceRepository.findCompetenceByCompetenceNameId(competenceNameId);

        for(Competence comp: competenceList){
            Profile prof = new Profile();
            prof.setPerson(profile.getPerson());
            prof.setCompetence(comp);
            prof.setYoe(profile.getYoe());
            profileRepository.save(prof);
        }
    }

    /**
     * Method that retrieves all profiles associated with the specified person
     * @param person the person's profiles
     */
    public List<Profile> getProfilesByPerson(Person person) {
        return profileRepository.findProfileByPerson(person);
    }

    /**
     * Method removes the specified profile.
     * @param profile , the profile to be removed
     */
    public void removeProfile(Profile profile) {
        profileRepository.delete(profile);
    }

    /**
     * Method finds profiles associated with the specified person and language.
     *
     * @param currentPerson , the person whoms profiles are to be found.
     * @param language, the desired language of the competence names
     * @return a list of profiles, the competence name in each competence in each profile is in the specified language.
     */
    public List<Profile> findProfilesByPersonAndCompetencesByLanguage(Person currentPerson, String language) {
       List<Profile> profiles =  profileRepository.findProfileByPersonAndCompetenceLanguageCode(currentPerson, language);

       return profiles;
    }
}