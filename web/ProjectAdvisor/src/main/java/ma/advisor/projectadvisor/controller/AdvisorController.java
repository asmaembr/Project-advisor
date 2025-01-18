package ma.advisor.projectadvisor.controller;

import jakarta.servlet.http.HttpSession;
import ma.advisor.projectadvisor.DTOs.Profile;
import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import ma.advisor.projectadvisor.service.AdvisorProxy;
import ma.advisor.projectadvisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorProxy advisorProxy;


    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("entrepreneur", new Entrepreneur());
        return "LoginRegisterForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Entrepreneur entrepreneur, Model model, HttpSession session) {
        Entrepreneur user = advisorService.getEntrepreneur(entrepreneur.getEmail());
        if (user != null && user.getPassword().equals(entrepreneur.getPassword())) {
            model.addAttribute("erreur", null);
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erreur", "Email ou Mot de passe erroné");
            return "LoginRegisterForm";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Entrepreneur entrepreneur, Model model) {
        if (advisorService.getEntrepreneur(entrepreneur.getEmail()) != null)
            model.addAttribute("erreur", "Email déja éxistant , Veillez réessayer !!");
        else {
            advisorService.saveEntrepreneur(entrepreneur);
            model.addAttribute("erreur", null);
        }
        return "LoginRegisterForm";
    }

    @GetMapping("/visitor")
    public String visitor(Model model) {
        model.addAttribute("toast", null);
        model.addAttribute("Profile_Valeurs", advisorProxy.getValeursProfile());
        model.addAttribute("range", Arrays.asList(1, 2, 3, 4, 5));
        model.addAttribute("profile", new Profile());
        return "ProfilePredictionForm";
    }


    @PostMapping("/profile")
    public String visitor(@ModelAttribute Profile profile, Model model) {
        model.addAttribute("Profile_Valeurs", advisorProxy.getValeursProfile());
        model.addAttribute("range", Arrays.asList(1, 2, 3, 4, 5));
        model.addAttribute("profile", profile);
        model.addAttribute("toast", advisorProxy.ProfilePrediction(profile).trim());
        return "ProfilePredictionForm";
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("erreur", null);
            model.addAttribute("user", (Entrepreneur) session.getAttribute("user"));
            model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            model.addAttribute("NouveauProjet", new Project());
            return "Dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @PostMapping("/new")
    public String newProject(@ModelAttribute Project project, HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            project.setEntrepreneur((Entrepreneur) session.getAttribute("user"));
            advisorService.saveProject(project);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @PostMapping("/profit")
    public String profit(@ModelAttribute Project project, Model model) {
        model.addAttribute("toast", advisorProxy.ProfitPrediction(project).trim());
        project.setProfit(advisorProxy.ProfitPrediction(project).trim());
        return "Dashboard";
    }

    @PostMapping("/top500")
    public String top500(@ModelAttribute Project project, Model model) {
        model.addAttribute("toast", advisorProxy.Top500Prediction(project).trim());
        project.setIsTop500(advisorProxy.Top500Prediction(project).trim());
        return "Dashboard";
    }





}
