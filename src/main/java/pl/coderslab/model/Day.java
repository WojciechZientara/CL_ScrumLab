package pl.coderslab.model;

public class Day {

    private int id;
    private String name;
    private int displayOrder;

    @Override
    public String toString() {
        return "Day [id=" + id + ", name=" + name + ", displayOrder=" + displayOrder + "]";
    }

    public Day() {
    }

    public Day(int id, String name, int displayOrder) {
        this.id = id;
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public int getId(int id) {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}