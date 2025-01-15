package ma.advisor.projectadvisor.controller;

import jakarta.servlet.http.HttpSession;
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
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model , HttpSession session) {
        Entrepreneur user = advisorService.getEntrepreneur(email);
        if(user!= null && user.getPassword().equals(password)){
            model.addAttribute("erreur",null);
            session.setAttribute("user",user);
            return "redirect:/dashboard";
        }else {
            model.addAttribute("erreur","Email ou Mot de passe erroné");
            return "LoginRegisterForm";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if(session.getAttribute("user") != null){
            model.addAttribute("erreur",null);
            model.addAttribute("user",(Entrepreneur) session.getAttribute("user"));
            return "Dashboard";
        }else {
            model.addAttribute("erreur","Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Entrepreneur entrepreneur, Model model) {
        if(advisorService.getEntrepreneur(entrepreneur.getEmail()) != null)
            model.addAttribute("erreur", "Email déja éxistant , Veillez réessayer !!");
        else {
            advisorService.saveEntrepreneur(entrepreneur);
            model.addAttribute("erreur",null);
        }
        return "LoginRegisterForm";
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
