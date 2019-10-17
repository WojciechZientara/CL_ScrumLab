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

@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Find Admin by email in DB
        AdminDao adminDao = new AdminDao();
        Admin loginAttempt = adminDao.readByEmail(email);

        //Check if Admin exists
        if (loginAttempt.getId() == 0) {
            //Failed login attempt; admin not found
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {

            //Admin exists; check password
            if (loginAttempt.isPasswordCorrect(password)) {
                //Successful login attempt; valid password
                HttpSession session = request.getSession();
                session.setAttribute("logged",true);
                session.setAttribute("id",loginAttempt.getId());
                session.setAttribute("name", loginAttempt.getFirstName());
                session.setAttribute("superAdmin", loginAttempt.isSuperAdmin());
                response.sendRedirect("/app");
            } else {
                //Failed login attempt; not valid password
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
