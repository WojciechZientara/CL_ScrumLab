package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class AppPlanEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Plan plan = new Plan();
        plan.setId(planId);
        plan.setName(name);
        plan.setDescription(description);
        plan.setAdminId(getAdminIdFromSession(request));
        System.out.println(plan.toString());

        PlanDao planDao = new PlanDao();
        planDao.update(plan);

        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));

        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(planId);

        request.setAttribute("plan", plan);

        getServletContext().getRequestDispatcher("/app/appPlanEdit.jsp").forward(request, response);
    }

    private int getAdminIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (int) session.getAttribute("id");
    }
}
