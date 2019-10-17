package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/edit-password/")
public class EditPassword extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("id");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.read(admin_id);
        admin.setPasswordWithHashing(request.getParameter("password"));
        adminDao.update(admin);
        response.sendRedirect("/app");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app/edit-password.jsp").forward(request, response);

    }

}
