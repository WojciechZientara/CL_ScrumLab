package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Check double e-mail
        Admin admin = new Admin(name, surname, email, password, false, true);
        AdminDao adminDao = new AdminDao();
        List<Admin> allAdmins = adminDao.findAll();
        boolean isEmailUnique = true;
        for (Admin listedAdmin : allAdmins) {
            if (listedAdmin.getEmail().equals(admin.getEmail())) {
                isEmailUnique = false;
            }
        }

        //Save to database or fail
        if (isEmailUnique) {
            adminDao.create(admin);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("admin",admin);
            getServletContext().getRequestDispatcher("/regfail.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
