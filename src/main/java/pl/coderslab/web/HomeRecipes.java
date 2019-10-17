package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/recipes")
public class HomeRecipes extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchPhrase = request.getParameter("searchPhrase");
        Pattern compiledPattern = Pattern.compile(searchPhrase, Pattern.CASE_INSENSITIVE);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAll();
        List<Recipe> searchRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            Matcher matcher = compiledPattern.matcher(recipe.getName());
            if (matcher.find()) {
                searchRecipes.add(recipe);
            }
        }

        request.setAttribute("recipes", searchRecipes);
        request.getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes = recipeDao.findAll();

        request.setAttribute("recipes", recipes);
        request.getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
    }
}
