package ma.advisor.projectadvisor.controller;

import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.repository.EntrepreneurRepository;
import ma.advisor.projectadvisor.repository.ProjectRepository;
import ma.advisor.projectadvisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("entrepreneur", new Entrepreneur());
        return "LoginRegisterForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return "Dashboard";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Entrepreneur entrepreneur) {
        advisorService.saveEntrepreneur(entrepreneur);
        return "redirect:/";
    }

    @GetMapping("/visitor")
    public String visitor(Model model) {
        model.addAttribute("entrepreneur", new Entrepreneur());
        return "ProfilePredictionForm";
    }
    @PostMapping("/visitor")
    public String visitor(@ModelAttribute Entrepreneur entrepreneur) {
        advisorService.ProfilePrediction(entrepreneur);
        return "ProfilePredictionForm";
    }


}
