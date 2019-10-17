package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/app/plan/delete")
public class AppPlanDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        Integer id = 0;
        try {
            id = Integer.parseInt(strId);
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            PlanDao planDao = new PlanDao();
            recipePlanDao.deleteAllRecipesInPlan(id);
            planDao.delete(id);
            request.getServletContext().getRequestDispatcher("/app/plan/list").forward(request,response);
        } catch (IllegalArgumentException e) {}
    }
}
