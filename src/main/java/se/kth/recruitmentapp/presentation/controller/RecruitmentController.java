package se.kth.recruitmentapp.presentation.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.service.ProfileService;

import java.util.List;

/**
 * Handles all HTTP routes to all recruitment related operations
 */
@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
    @Autowired
    private ProfileService profileService;

    /**
     * Get request for the applications view page.
     * @param model Model objects used by the page.
     * @param page Which page to fetch
     * @param size Number of profiles to fetch
     * @return the applicants page URL
     */
    @GetMapping
    public String showApplicants(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        int nextPageNumber = page + 1;
        List<Profile> profiles = profileService.getProfiles(page, size);
        model.addAttribute("page", nextPageNumber);
        if (profiles.isEmpty()) {
            model.addAttribute("page", null);
        }
        model.addAttribute("profiles", profiles);
        return "applicants";
    }

}
