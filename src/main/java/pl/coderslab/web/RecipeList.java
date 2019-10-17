package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@WebServlet("/app/recipe/list/")
public class RecipeList extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        RecipeDao recipeDao = new RecipeDao();
        if (session.getAttribute("id") != null) {
            int adminId = Integer.valueOf(session.getAttribute("id").toString());
            Admin admin = adminDao.read(adminId);
            List<Recipe> recipeList = recipeDao.findAllByAdminId(adminId);
            request.setAttribute("adminName", admin.getFirstName());
            request.setAttribute("recipeList", recipeList);

            try {
                String deleted = request.getParameter("deleted");
                if (deleted.equals("failed")) {
                    request.setAttribute("deleted", "failed");
                }
            } catch (NullPointerException e) {}

            request.getRequestDispatcher("/app/recipes.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/login").forward(request, response);
        }

    }
}
