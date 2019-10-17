package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")

public class AppRecipePlanAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int plan_id = Integer.parseInt(request.getParameter("plan_id"));
        String meal_name = request.getParameter("meal_name");
        int display_order = Integer.parseInt(request.getParameter("display_order"));
        int recipe_id = Integer.parseInt(request.getParameter("recipe_id"));
        int day_name_id = Integer.parseInt(request.getParameter("day_name_id"));

        RecipePlan recipePlan = new RecipePlan(recipe_id, meal_name, display_order,day_name_id,plan_id);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.create(recipePlan);

        response.sendRedirect("/app/recipe/plan/add");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        int adminId = Integer.valueOf(session.getAttribute("id").toString());

        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAllByAdminId(adminId);
        request.setAttribute("planList",planList);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.findAllByAdminId(adminId);
        request.setAttribute("recipeList", recipeList);

        getServletContext().getRequestDispatcher("/app/appRecipePlanAdd.jsp").forward(request, response);

    }

}
