package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDao {
    // ZAPYTANIA SQL
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ALL_PLANS_BY_ADMIN_QUERY = "SELECT * FROM plan where admin_id = ? ORDER BY created DESC;";
    private static final String COUNT_ADMIN_PLANS_QUERY = "SELECT count(*) FROM plan WHERE admin_id = ?;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, admin_id = ? WHERE id = ?;";
    private static final String LAST_PLAN_QUERY = "SELECT day_name.name as day_name, recipe_plan.day_name_id as day_id, meal_name, recipe.name as recipe_name, recipe.id as recipe_id, plan.name as plan_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id = day_name_id\n" +
            "JOIN plan on plan.id = plan_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) -- zamiast 1 należy wstawić id użytkownika (tabela admins) --\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String FULL_PLAN_BY_ID_QUERY = "SELECT day_name.name as day_name, recipe_plan.day_name_id as day_id, meal_name, recipe.name as recipe_name, recipe.id as recipe_id, plan.name as plan_name, plan.description as plan_description, recipe.description as recipe_description, recipe_plan.id as recipe_plan_id\n" +
            "FROM recipe_plan\n" +
            "JOIN day_name on day_name.id = recipe_plan.day_name_id\n" +
            "JOIN plan on plan.id = recipe_plan.plan_id\n" +
            "JOIN recipe on recipe.id = recipe_plan.recipe_id\n" +
            "WHERE recipe_plan.plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order";

    /**
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public Plan read(Integer planId) {

        Plan plan = new Plan();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getTimestamp("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plan;

    }

    /**
     * Return all plans
     *
     * @return
     */
    public List<Plan> findAll() {

        List<Plan> planList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    /**
     * Return all recipes by admin_id
     * @param adminId
     * @return
     */
    public List<Plan> findAllByAdminId(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
        ) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_BY_ADMIN_QUERY);
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    /**
     * Return last plan
     *
     * @return
     */
    public List<LatestPlan> latestPlanDetails(Integer admin_id) {

        List<LatestPlan> planListArray = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LAST_PLAN_QUERY)
        ) {
            statement.setInt(1, admin_id);

            try (ResultSet resultSet = statement.executeQuery()) {

                int counter = 1;

                while (resultSet.next()) {

                    LatestPlan planList = new LatestPlan();

                    planList.setId(counter);
                    planList.setRecipe_id(resultSet.getInt("recipe_id"));
                    planList.setPlan_name(resultSet.getString("plan_name"));
                    planList.setDay_name(resultSet.getString("day_name"));
                    planList.setDay_id(resultSet.getInt("day_id"));
                    planList.setMeal_name(resultSet.getString("meal_name"));
                    planList.setRecipe_name(resultSet.getString("recipe_name"));
                    planList.setRecipe_description(resultSet.getString("recipe_description"));

                    planListArray.add(planList);

                    counter++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return planListArray;

    }

    public List<LatestPlan> fullPlanDetaildById(int planId) {

        List<LatestPlan> planListArray = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FULL_PLAN_BY_ID_QUERY)) {
            statement.setInt(1, planId);

            try (ResultSet resultSet = statement.executeQuery()) {

                int counter = 1;

                while (resultSet.next()) {

                    LatestPlan planList = new LatestPlan();

                    planList.setId(counter);
                    planList.setRecipe_id(resultSet.getInt("recipe_id"));
                    planList.setPlan_name(resultSet.getString("plan_name"));
                    planList.setPlan_description(resultSet.getString("plan_description"));
                    planList.setDay_name(resultSet.getString("day_name"));
                    planList.setDay_id(resultSet.getInt("day_id"));
                    planList.setMeal_name(resultSet.getString("meal_name"));
                    planList.setRecipe_name(resultSet.getString("recipe_name"));
                    planList.setRecipe_description(resultSet.getString("recipe_description"));
                    planList.setRecipe_plan_id(resultSet.getInt("recipe_plan_id"));

                    planListArray.add(planList);

                    counter++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return planListArray;

    }

    /**
     * Return all plans by logged in admin
     * Session ID of current loggedn in user must be passed as parameter
     *
     * @return
     */
    public int countAdminPlans(Integer admin_id) {

        int numberRow = 0;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ADMIN_PLANS_QUERY)
        ) {
            statement.setInt(1, admin_id);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    numberRow = resultSet.getInt(1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return numberRow;
    }

    /**
     * Create plan
     *
     * @param plan
     * @return
     */
    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setTimestamp(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
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
     * Remove plan by id
     *
     * @param planId
     */
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            int deletedRows = statement.executeUpdate();

            if (deletedRows == 0) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Update plan
     *
     * @param plan
     */
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getAdminId());
            statement.setInt(4, plan.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}