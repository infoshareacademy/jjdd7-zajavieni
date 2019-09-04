package com.isa.zajavieni.servlet;

import com.isa.zajavieni.provider.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());
    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider.getTemplate(getServletContext(), "main-template.ftlh");
        Map<String, Object> model = new HashMap<>();
        try{
            template.process(model, resp.getWriter());
        } catch (TemplateException e){
            logger.severe(e.getMessage());
        }
    }
}
