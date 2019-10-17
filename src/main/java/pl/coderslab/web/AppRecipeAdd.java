package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/app/recipe/add")
public class AppRecipeAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Recipe recipe = new Recipe(name, ingredients, description, ts, ts, preparationTime, preparation, getAdminIdFromSession(request));

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.create(recipe);

        response.sendRedirect("/app/recipe/list/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/appRecipeAdd.jsp").forward(request,response);
    }

    private int getAdminIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (int) session.getAttribute("id");
    }
}
