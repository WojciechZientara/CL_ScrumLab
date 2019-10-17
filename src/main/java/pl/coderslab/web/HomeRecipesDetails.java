package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recipes/details")
public class HomeRecipesDetails extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);

        request.setAttribute("recipe", recipe);
        request.getServletContext().getRequestDispatcher("/recipesDetails.jsp").forward(request, response);
    }
}
