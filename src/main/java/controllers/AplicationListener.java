package controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpSessionListener;

public class AplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("app initializing");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "everyone from the app");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("initializing request");
        sre.getServletContext().setAttribute("message", "saving value for the request");
    }
}