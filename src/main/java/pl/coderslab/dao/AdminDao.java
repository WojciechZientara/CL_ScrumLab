package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin, enable) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY= "SELECT * FROM admins;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String READ_ADMIN_QUERY_BY_EMAIL = "SELECT * FROM admins WHERE email = ?;";

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            //Convert boolean to int for admins tinyint
            int superAdminInt = 0;
            if (admin.isSuperAdmin()) {
                superAdminInt = 1;
            }

            int enableInt = 0;
            if (admin.isEnable()) {
                enableInt = 1;
            }

            prepStmt.setString(1, admin.getFirstName());
            prepStmt.setString(2, admin.getLastName());
            prepStmt.setString(3, admin.getEmail());
            prepStmt.setString(4, admin.getPassword());
            prepStmt.setInt(5, superAdminInt);
            prepStmt.setInt(6, enableInt);
            int result = prepStmt.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = prepStmt.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(READ_ADMIN_QUERY)) {
             prepStmt.setInt(1, adminId);

             try (ResultSet resultSet = prepStmt.executeQuery()) {
                while (resultSet.next()) {
                    readAdminResultSet(resultSet, admin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = prepStmt.executeQuery()) {

            while (resultSet.next()) {
                Admin admin = new Admin();
                readAdminResultSet(resultSet, admin);
                adminList.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;

    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            //Convert boolean to int for admins tinyint
            int superAdminInt = 0;
            if (admin.isSuperAdmin()) {
                superAdminInt = 1;
            }

            int enableInt = 0;
            if (admin.isEnable()) {
                enableInt = 1;
            }

            prepStmt.setString(1, admin.getFirstName());
            prepStmt.setString(2, admin.getLastName());
            prepStmt.setString(3, admin.getEmail());
            prepStmt.setString(4, admin.getPassword());
            prepStmt.setInt(5, superAdminInt);
            prepStmt.setInt(6, enableInt);
            prepStmt.setInt(7, admin.getId());

            prepStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement prepStmt = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            prepStmt.setInt(1, adminId);
            int affectedRows = prepStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readAdminResultSet(ResultSet resultSet, Admin admin) throws SQLException {
        //Convert tinyint to boolean for Admin class
        boolean isSuperAdmin = false;
        if (resultSet.getInt("superadmin") == 1) {
            isSuperAdmin = true;
        }

        boolean isEnable = false;
        if (resultSet.getInt("enable") == 1) {
            isEnable = true;
        }

        //Update admin with resultSet
        admin.setId(resultSet.getInt("id"));
        admin.setFirstName(resultSet.getString("first_name"));
        admin.setLastName(resultSet.getString("last_name"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPassword(resultSet.getString("password"));
        admin.setSuperAdmin(isSuperAdmin);
        admin.setEnable(isEnable);
    }

    public Admin readByEmail(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(READ_ADMIN_QUERY_BY_EMAIL)) {
            prepStmt.setString(1, email);

            try (ResultSet resultSet = prepStmt.executeQuery()) {
                while (resultSet.next()) {
                    readAdminResultSet(resultSet, admin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }
}
