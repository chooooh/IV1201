package se.kth.recruitmentapp.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.kth.recruitmentapp.domain.Profile;
import se.kth.recruitmentapp.service.ProfileService;

import java.util.List;

@Controller
@RequestMapping("/recruitment")
public class RecruitmentController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public String showApplicants(Model model) {
        List<Profile> profiles = profileService.getProfiles();
        model.addAttribute("profiles", profiles);
        return "applicants";
    }
}
