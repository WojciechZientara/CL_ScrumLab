package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/app/plan/add")
public class AppAddPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        int adminId = Integer.valueOf(session.getAttribute("id").toString());
        PlanDao planDao = new PlanDao();
        Plan plan = new Plan(planName, planDescription, new Timestamp(System.currentTimeMillis()), adminId);
        planDao.create(plan);
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/app/appAddPlan.jsp").forward(request,response);
    }
}
