package se.kth.recruitmentapp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.service.ProfileService;

import java.util.List;

/**
 * Handles all HTTP routes to all recruitment related operations
 */
@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {

    static final String RECRUITMENT_URL = "recruitment";

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
    public String showApplicants(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "15") int size) {
        if (page < 0) {
            return "redirect:/recruitment";
        }

        Pageable pageable = PageRequest.of(page, size);
        PageImpl<Profile> profilePageImpls = profileService.getProfilePages(pageable);

        if (page > profilePageImpls.getTotalPages() - 1) {
            return "redirect:/recruitment?page=" + (profilePageImpls.getTotalPages() - 1);
        }

        if (profilePageImpls.hasPrevious()) {
            model.addAttribute("prevPageNumber", page - 1);
        }
        if (profilePageImpls.hasNext()) {
            model.addAttribute("nextPageNumber", page + 1);
        }

        List<Profile> profiles = profilePageImpls.toList();
        model.addAttribute("profiles", profiles);
        return "applicants";
    }

}
