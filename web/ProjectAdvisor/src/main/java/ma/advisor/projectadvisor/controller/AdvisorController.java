package ma.advisor.projectadvisor.controller;

import ma.advisor.projectadvisor.repository.EntrepreneurRepository;
import ma.advisor.projectadvisor.repository.ProjectRepository;
import ma.advisor.projectadvisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdvisorController {
    @Autowired
    private EntrepreneurRepository entrepreneurRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AdvisorService advisorService;

    @GetMapping("/login")
    public String login() {
        return "loginform";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return "dashboard";
    }

    @PostMapping("/register")
    public void register() {
    }


}
