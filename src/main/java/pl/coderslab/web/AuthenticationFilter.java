package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class AuthenticationFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        req.setCharacterEncoding("UTF-8");
        try {
            if (session.getAttribute("logged").toString().equals("true")) {
                chain.doFilter(req, resp);
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            response.sendRedirect("/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
