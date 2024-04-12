package org.farid.api.model.headers.webappheaders;

import Service.StudentService;
import Service.StudentServiceImpl;
import dto.StudentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/students.xls","/students.html","/students"})
public class StudentXLS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService service = new StudentServiceImpl();
        List<StudentDto> studentsDtos = service.listar();
        resp.setContentType("Text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXLS = servletPath.endsWith(".xls");
        if (esXLS) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=students.xls");
        }
        // Abre un PrintWriter para escribir la respuesta.
        try (PrintWriter out = resp.getWriter()) {
            if (!esXLS) {
                // Si no es una solicitud .xls, genera una página HTML con la lista de estudiantes.
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Listado de Estudiantes</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Listado de Estudiantes!</h1>");
                out.println("<p><a href=\"" + req.getContextPath()
                        + "/students.xls" + "\">exportar a xls</a></p>");
                out.println("<p><a href=\"" + req.getContextPath()
                        + "/students.json" + "\">mostrar json</a></p>");
            }
            // Genera una tabla HTML con los detalles de los estudiantes.
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            // Itera sobre la lista de estudiantes y genera una fila HTML para cada uno.
            studentsDtos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.id() + "</td>");
                out.println("<td>" + p.name() + "</td>");
                out.println("<td>" + p.email() + "</td>");
                out.println("<td>" + p.semestre() + "</td>");
                out.println("</tr>");
            });
            // Cierra la tabla.
            out.println("</table>");
            // Si no es una solicitud .xls, cierra la página HTML.
            if (!esXLS) {
                out.println(" </body>");
            }
        }
    }
}
