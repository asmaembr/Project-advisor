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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {


    private List<Project> generateProjects() {
        List<Project> projects = new ArrayList<>();

        Entrepreneur entrepreneur1 = new Entrepreneur();
        Entrepreneur entrepreneur2 = new Entrepreneur();
        projects.add(new Project(1, "name1", "Casablanca", 100, 3, 5000000L, "Technology", "Oui", 50, "Yes", "North", 100000.0, 50000.0, 30000.0, "High", entrepreneur1));

        projects.add(new Project(2, "name2", "Rabat", 50, 2, 2000000L, "Healthcare", "Non", 30, "No", "Central", 80000.0, 30000.0, 20000.0, "Moderate", entrepreneur2));

        projects.add(new Project(3, "name3", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(4, "name4", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(5, "name5", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(6, "name3", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(7, "name4", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(8, "name5", "Marrakech", 80, 4, 3000000L, "Tourism", "Non", 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));

        return projects;
    }


    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorProxy advisorProxy;

    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("ProfitReponse", advisorProxy.getValeursProfit());
            model.addAttribute("Top500Reponse", advisorProxy.getValeursTop500());
            model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            model.addAttribute("project", new ProjectDTO());
            model.addAttribute("toast", null);
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
    public String deleteProject(HttpSession session, Model model, @PathVariable Integer id) {
        if (session.getAttribute("user") != null) {
            advisorService.deleteProject(id);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erreur", "Vous n'avez pas de compte , veillez s'inscrire !!! ");
            return "LoginRegisterForm";
        }
    }

    @GetMapping("/modify/{id}")
    public String modifyProject(HttpSession session, Model model, @PathVariable Integer id) {
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
    public String profit( Model model  ,@PathVariable  Integer id , HttpSession session) {
        Project projet = advisorService.getProject(id);
        model.addAttribute("toast", advisorProxy.ProfitPrediction(projet));
        projet.setProfit(advisorProxy.ProfitPrediction(projet));
        model.addAttribute("ProfitReponse", advisorProxy.getValeursProfit());
        model.addAttribute("Top500Reponse", advisorProxy.getValeursTop500());
        model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
        model.addAttribute("project", new ProjectDTO());
        return "Dashboard";
    }

    @PostMapping("/top500/{id}")
    public String top500( Model model  ,@PathVariable  Integer id,HttpSession session) {
        Project projet = advisorService.getProject(id);
        model.addAttribute("toast", advisorProxy.Top500Prediction(projet));
        projet.setIsTop500(advisorProxy.Top500Prediction(projet));
        model.addAttribute("ProfitReponse", advisorProxy.getValeursProfit());
        model.addAttribute("Top500Reponse", advisorProxy.getValeursTop500());
        model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
        model.addAttribute("project", new ProjectDTO());
        return "Dashboard";
    }


}
