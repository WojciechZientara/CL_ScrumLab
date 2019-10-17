package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/plan/recipe/delete")
public class AppPlanRecipeDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String strPlanId = request.getParameter("planid");
        try {
            int id = Integer.parseInt(strId);
            int planId = Integer.parseInt(strPlanId);
            RecipePlanDao recipePlanDao = new RecipePlanDao();
            recipePlanDao.delete(id);
            response.sendRedirect("/app/plan/details?id=" + planId);
        } catch (IllegalArgumentException e) {}
    }
}
