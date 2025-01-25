package ma.advisor.projectadvisor.controller;

import jakarta.servlet.http.HttpSession;
import ma.advisor.projectadvisor.DTOs.ProjectDTO;
import ma.advisor.projectadvisor.model.Entrepreneur;
import ma.advisor.projectadvisor.model.Project;
import ma.advisor.projectadvisor.service.AdvisorProxy;
import ma.advisor.projectadvisor.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorProxy advisorProxy;

    public static String toast = null;
    
    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("ProfitReponse", advisorProxy.getValeursProfit());
            model.addAttribute("Top500Reponse", advisorProxy.getValeursTop500());
            model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            model.addAttribute("project", new ProjectDTO());
            model.addAttribute("toast",toast);
            return "Dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @PostMapping("/new")
    public String newProject(@ModelAttribute ProjectDTO project, HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            Project newprojet = new Project();
            newprojet.setId(project.getId());
            newprojet.setName(project.getName());
            newprojet.setVille(project.getVille());
            newprojet.setNombre_relations(project.getNombre_relations());
            newprojet.setTours_financement(project.getTours_financement());
            newprojet.setCapitale_fonds(project.getCapitale_fonds());
            newprojet.setCategorie(project.getCategorie());
            newprojet.setInvestisseurs_providentiels(project.getInvestisseurs_providentiels());
            newprojet.setNombre_participants(project.getNombre_participants());
            newprojet.setRegion(project.getRegion());
            newprojet.setRnd(project.getRnd());
            newprojet.setMarketing(project.getMarketing());
            newprojet.setAdministration(project.getAdministration());
            newprojet.setEntrepreneur((Entrepreneur) session.getAttribute("user"));
            advisorService.saveProject(newprojet);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(HttpSession session, Model model, @PathVariable Long id) {
        if (session.getAttribute("user") != null) {
            advisorService.deleteProject(id);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @GetMapping("/modify/{id}")
    public String modifyProject(HttpSession session, Model model, @PathVariable Long id) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("project", advisorService.getProject(id));
            model.addAttribute("ProfitReponse", advisorProxy.getValeursProfit());
            model.addAttribute("Top500Reponse", advisorProxy.getValeursTop500());
            model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            return "Dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }


    @PostMapping("/profit/{id}")
    public String profit( Model model  ,@PathVariable  Long id , HttpSession session) {
        Project projet = advisorService.getProject(id);
        String profit = advisorProxy.ProfitPrediction(projet).replace("\"","" );
        toast= profit;
        projet.setProfit(profit);
        advisorService.saveProject(projet);
        return "redirect:/dashboard";
    }

    @PostMapping("/top500/{id}")
    public String top500( Model model  ,@PathVariable  Long id,HttpSession session) {
        Project projet = advisorService.getProject(id);
        String top500 = advisorProxy.Top500Prediction(projet).replace("\"","" );
        toast= top500;
        projet.setIsTop500(top500);
        advisorService.saveProject(projet);
        return "redirect:/dashboard";
    }


}
