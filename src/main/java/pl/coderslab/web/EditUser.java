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

@WebServlet("/app/edit-user/")
public class EditUser extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("id");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.read(admin_id);
        admin.setFirstName(request.getParameter("name"));
        admin.setLastName(request.getParameter("surname"));
        admin.setEmail(request.getParameter("email"));
        adminDao.update(admin);
        session.setAttribute("name", admin.getFirstName());
        request.setAttribute("admin",admin);
        response.sendRedirect("/app");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer admin_id = (Integer) session.getAttribute("id");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.read(admin_id);
        request.setAttribute("admin",admin);
        getServletContext().getRequestDispatcher("/app/edit-user.jsp").forward(request, response);

    }

}
