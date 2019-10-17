package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RecipePlanDao {

    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String DELETE_ALL_RECIPES_IN_PLAN_QUERY = "DELETE FROM recipe_plan WHERE plan_id = ?";
    /**
     * Create recipePlan
     *
     * @param recipePlan
     * @return
     */
    public RecipePlan create(RecipePlan recipePlan) {

        try (Connection connection = DbUtil.getConnection();

             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setInt(1, recipePlan.getRecipe_id());
            insertStm.setString(2, recipePlan.getMeal_name());
            insertStm.setInt(3, recipePlan.getDisplay_order());
            insertStm.setInt(4, recipePlan.getDay_name_id());
            insertStm.setInt(5, recipePlan.getPlan_id());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
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
     * Remove recipe_plan by id
     *
     * @param recipePlanId
     */
    public void delete(Integer recipePlanId) {
        try (Connection connection = DbUtil.getConnection();


             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, recipePlanId);
            int deletedRows = statement.executeUpdate();

            if (deletedRows == 0) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllRecipesInPlan(Integer recipePlanId) {
        try (Connection connection = DbUtil.getConnection()) {
                PreparedStatement deleteRecipesStatement = connection.prepareStatement(DELETE_ALL_RECIPES_IN_PLAN_QUERY);
                deleteRecipesStatement.setInt(1, recipePlanId);
                int deletedRows = deleteRecipesStatement.executeUpdate();
                if (deletedRows == 0) {
                    throw new NotFoundException("Product not found");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
