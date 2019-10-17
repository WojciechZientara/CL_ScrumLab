package pl.coderslab.dao;

import pl.coderslab.model.Day;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayDao {

    private static final String FIND_ALL_DAYS_QUERY = "SELECT * FROM day_name ORDER BY display_order ASC;";

    public List<Day> findAll() {
        List<Day> dayList = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(FIND_ALL_DAYS_QUERY);
            ResultSet resultSet = prepStmt.executeQuery()) {

            while (resultSet.next()) {
                Day day = new Day();
                day.setId(resultSet.getInt("id"));
                day.setName(resultSet.getString("name"));
                day.setDisplayOrder(resultSet.getInt("display_order"));
                dayList.add(day);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayList;
    }
}