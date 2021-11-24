package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.type.IntersectionType;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
@Component(service = Servlet.class, property = {
        "sling.servlet.paths=" + "/bin/action/coffeeConfirm",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST
})
public class CoffeeConfirmServlet extends SlingAllMethodsServlet {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String coffeeType = request.getParameter("coffeeType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        LOGGER.info("Entering the main code...");

        if(coffeeType.equals("Americano")){
            out.print("Your bill amounts to $" + quantity * 50);
        } else if(coffeeType.equals("Espresso")){
            out.print("Your bill amounts to $" + quantity * 45);
        } else if(coffeeType.equals("Dopio")){
            out.print("Your bill amounts to $" + quantity * 55);
        } else if(coffeeType.equals("Cortado")){
            out.print("Your bill amounts to $" + quantity * 60);
        } else if(coffeeType.equals("RedEye")){
            out.print("Your bill amounts to $" + quantity * 75);
        }

        LOGGER.info("Exiting the main code...");

        out.println();
        out.print("Order confirmed. Please collect your orders from the counter");

    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write("Testing");
    }
}