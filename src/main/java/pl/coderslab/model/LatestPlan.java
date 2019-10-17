package pl.coderslab.model;

public class LatestPlan {

    private Integer id;
    private String plan_name;
    private String plan_description;
    private String day_name;
    private Integer day_id;
    private String meal_name;
    private String recipe_name;
    private String recipe_description;
    private Integer recipe_id;
    private Integer recipe_plan_id;

    public LatestPlan() {

    }

    public LatestPlan(Integer id, String plan_name, String day_name, Integer day_id, String meal_name, String recipe_name, String recipe_description, Integer recipe_id, Integer recipe_plan_id) {
        this.id = id;
        this.plan_name = plan_name;
        this.day_name = day_name;
        this.day_id = day_id;
        this.meal_name = meal_name;
        this.recipe_name = recipe_name;
        this.recipe_description = recipe_description;
        this.recipe_id = recipe_id;
        this.recipe_plan_id = recipe_plan_id;
    }

    public Integer getDay_id() {
        return day_id;
    }

    public void setDay_id(Integer day_id) {
        this.day_id = day_id;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_description() {
        return recipe_description;
    }

    public void setRecipe_description(String recipe_description) {
        this.recipe_description = recipe_description;
    }

    public String getPlan_description() {
        return plan_description;
    }

    public void setPlan_description(String plan_description) {
        this.plan_description = plan_description;
    }

    public Integer getRecipe_plan_id() {
        return recipe_plan_id;
    }

    public void setRecipe_plan_id(Integer recipe_plan_id) {
        this.recipe_plan_id = recipe_plan_id;
    }
}
