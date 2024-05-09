package controllers;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapper.dto.ClientDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static  String PASWORD = "12345";
    @JacksonInject
    @Named("login")
    LoginService auth;
    @JacksonInject
    ClientService cService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password =  req.getParameter("password");
        if(USERNAME.equals(username) && PASWORD.equals(password)) {
            List<ClientDto> clientDTOList = cService.list();
            getServletContext().setAttribute("clientDTOservice", clientDTOList);
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.setContentType("text/html; charset = UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset =\"UTF-8\">");
                out.println("<title> Correct Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Correct Login!</h1>");
                out.println("<h3>" + username + " you´ve logged succesfully!</h3>");
                out.println("</body>");
                out.println("/html");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sorry, not authorized " +
                    "to enter this page");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection)  req.getAttribute("conn");
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()){
            resp.setContentType("text/html; charset = UTF-8");
            try (PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset =\"UTF-8\">");
                out.println("<title> Hola " +usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + "</h1>");
                out.println("<p><a href='>" + req.getContextPath() + "/index.html'> volver </a> </p>");
                out.println("<p><a href='>" + req.getContextPath() + "/logout'> cerrar sesión </a> </p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
