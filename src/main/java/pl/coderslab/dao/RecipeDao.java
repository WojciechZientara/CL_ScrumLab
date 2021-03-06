package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    // ZAPYTANIA SQL
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String FIND_ALL_RECIPES_BY_ADMIN_QUERY = "SELECT * FROM recipe where admin_id = ?;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String COUNT_RECIPES_BY_USER_QUERY = "SELECT COUNT(*) FROM recipe where admin_id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ?, ingredients = ?, description = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";

    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getTimestamp("created"));
                    recipe.setUpdated(resultSet.getTimestamp("updated"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }

    /**
     * Return all recipes
     *
     * @return
     */
    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created"));
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    /**
     * Return all recipes by admin_id
     * @param adminId
     * @return
     */
    public List<Recipe> findAllByAdminId(int adminId) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
        ) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_BY_ADMIN_QUERY);
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getTimestamp("created"));
                recipeToAdd.setUpdated(resultSet.getTimestamp("updated"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;

    }

    /**
     * Create recipe
     *
     * @param recipe
     * @return
     */
    public Recipe create(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            insertStm.setTimestamp(4, recipe.getCreated());
            insertStm.setTimestamp(5, recipe.getUpdated());
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Remove recipe by id
     *
     * @param recipeId
     */
    public void delete(Integer recipeId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            int deletedRows = statement.executeUpdate();

            if (deletedRows == 0) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update recipe
     *
     * @param recipe
     */
    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(8, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            statement.setTimestamp(4, recipe.getUpdated());
            statement.setInt(5, recipe.getPreparationTime());
            statement.setString(6, recipe.getPreparation());
            statement.setInt(7, recipe.getAdminId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get number of HomeRecipes by id
     *
     * @return
     */
    public int getNumberOfRecipesById(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_RECIPES_BY_USER_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getInt("count(*)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}