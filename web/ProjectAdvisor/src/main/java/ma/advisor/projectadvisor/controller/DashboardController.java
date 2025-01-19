package ma.advisor.projectadvisor.controller;

import jakarta.servlet.http.HttpSession;
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
        projects.add(new Project(1, "name1", "Casablanca", 100, 3, 5000000L, "Technology", 10, 50, "Yes", "North", 100000.0, 50000.0, 30000.0, "High", entrepreneur1));

        projects.add(new Project(2, "name2", "Rabat", 50, 2, 2000000L, "Healthcare", 5, 30, "No", "Central", 80000.0, 30000.0, 20000.0, "Moderate", entrepreneur2));

        projects.add(new Project(3, "name3", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(4, "name4", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(5, "name5", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(6, "name3", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(7, "name4", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));
        projects.add(new Project(8, "name5", "Marrakech", 80, 4, 3000000L, "Tourism", 8, 40, "Yes", "South", 90000.0, 40000.0, 25000.0, "High", entrepreneur1));

        return projects;
    }


    @Autowired
    private AdvisorService advisorService;
    @Autowired
    private AdvisorProxy advisorProxy;

    @GetMapping
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("erreur", null);
            model.addAttribute("user", (Entrepreneur) session.getAttribute("user"));
            //model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            model.addAttribute("projects", generateProjects());
            model.addAttribute("project", new Project());
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
            model.addAttribute("projects", advisorService.getProjects((Entrepreneur) session.getAttribute("user")));
            model.addAttribute("project", advisorService.getProject(id));
            return "Dashboard";
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
