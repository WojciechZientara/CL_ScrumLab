package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet("/app/recipe/delete")
public class AppRecipeDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(strId);
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.delete(id);
            Recipe recipeExistsCheck = recipeDao.read(id);
            if (recipeExistsCheck.getId() == 0) {
                request.getServletContext().getRequestDispatcher("/app/recipe/list/").forward(request,response);
            } else {
                request.getServletContext().getRequestDispatcher("/app/recipe/list/?deleted=failed").forward(request,response);
            }
        } catch (IllegalArgumentException e) {}
    }
}
