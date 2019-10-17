package pl.coderslab.web;

import pl.coderslab.dao.DayDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Day;
import pl.coderslab.model.LatestPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/details")
public class AppPlanDetails extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));

        PlanDao planDao = new PlanDao();
        List<LatestPlan> list = planDao.fullPlanDetaildById(planId);
        request.setAttribute("list", list);

        DayDao dayDao = new DayDao();
        List<Day> days = dayDao.findAll();
        request.setAttribute("days", days);

        request.setAttribute("selectedPlanId", planId);

        getServletContext().getRequestDispatcher("/app/appPlanDetails.jsp").forward(request, response);
    }
}
