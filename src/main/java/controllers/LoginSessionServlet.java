package controllers;

import Service.Impl.LoginServiceSessionImpl;
import Service.LoginService;
import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/loginSession")
public class LoginSessionServlet extends HttpServlet {
    @JacksonInject
    @Named("loginSession")
    LoginService auth;

    final static String USERNAME = "admin";
    final static  String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            String menssageRequest = (String) req.getAttribute("message");
            String messageApp = (String) req.getServletContext().getAttribute("message");
            HttpSession session = req.getSession();
            session.getAttribute("username" /*username*/);
            resp.sendRedirect(req.getContextPath() + "/loginSession.jsp");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, you are not authorized to enter this page");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + usernameOptional.get() +
                        "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + usernameOptional.get() + " you have been " +  "login successfully!</h1>" );
                out.println("<p><a href='" + req.getContextPath() +
                        "/index.html'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,
                    resp);
        }
    }
}
}
