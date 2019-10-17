package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.LatestPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app")
public class App extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("id");

        PlanDao planDao = new PlanDao();
        Integer adminPlans = planDao.countAdminPlans(admin_id);
        request.setAttribute("adminPlans", adminPlans);

        RecipeDao recipeDao = new RecipeDao();
        Integer adminRecipes = recipeDao.getNumberOfRecipesById(admin_id);
        request.setAttribute("adminRecipes", adminRecipes);

        List<LatestPlan> lista = planDao.latestPlanDetails(admin_id);
        request.setAttribute("lista", lista);

        getServletContext().getRequestDispatcher("/app/index.jsp").forward(request, response);

    }

}
