package ma.advisor.projectadvisor.controller;

import jakarta.servlet.http.HttpSession;
import ma.advisor.projectadvisor.DTOs.Profile;
import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Sexe;
import ma.advisor.projectadvisor.service.AdvisorProxy;
import ma.advisor.projectadvisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorProxy advisorProxy;


    @GetMapping
    public String login(Model model, HttpSession session) {
        session.removeAttribute("user");
        model.addAttribute("entrepreneur", new Entrepreneur());
        return "LoginRegisterForm";
    }

    @PostMapping("login")
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

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("register")
    public String register(@ModelAttribute Entrepreneur entrepreneur, Model model) {
        if (advisorService.getEntrepreneur(entrepreneur.getEmail()) != null)
            model.addAttribute("erreur", "Email déja éxistant , Veillez réessayer !!");
        else {
            advisorService.saveEntrepreneur(entrepreneur);
            model.addAttribute("erreur", null);
        }
        return "LoginRegisterForm";
    }

    @GetMapping("profile")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("toast", null);
        model.addAttribute("Profile_Valeurs", advisorProxy.getValeursProfile());
        model.addAttribute("range", Arrays.asList(1, 2, 3, 4, 5));
        if (session.getAttribute("user") == null) {
            model.addAttribute("profile", new Profile());
        } else {
            Entrepreneur entrepreneur = (Entrepreneur) session.getAttribute("user");
            Profile profile = new Profile();
            profile.setAge(entrepreneur.getAge());
            profile.setEducation(entrepreneur.getEducation());
            profile.setGenre(entrepreneur.getGender().toString());
            profile.setTraitsCles(entrepreneur.getTraitCles());
            model.addAttribute("profile", profile);
        }
        return "ProfilePredictionForm";
    }


    @PostMapping("profile")
    public String profile(@ModelAttribute Profile profile, Model model , HttpSession session) {
        if( session.getAttribute("user") != null){
            Entrepreneur entrepreneur = (Entrepreneur) session.getAttribute("user");
            entrepreneur.setAge(profile.getAge());
            entrepreneur.setEducation(profile.getEducation());
            entrepreneur.setGender(Sexe.valueOf(profile.getGenre()));
            entrepreneur.setTraitCles(profile.getTraitsCles());
            advisorService.saveEntrepreneur(entrepreneur);
        }
        model.addAttribute("toast", advisorProxy.ProfilePrediction(profile).trim());
        model.addAttribute("Profile_Valeurs", advisorProxy.getValeursProfile());
        model.addAttribute("range", Arrays.asList(1, 2, 3, 4, 5));
        model.addAttribute("profile", profile);
        return "ProfilePredictionForm";

    }

    @GetMapping("retour")
    public String retour(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/";
        }
    }

}
