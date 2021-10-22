/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mysite.core.servlets;

import java.io.IOException;

import com.mysite.core.services.impl.ClassConfigurationService;
import com.mysite.core.services.impl.StudentClassService;
import com.mysite.core.services.student;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.mysite.core.services.CalculatorService;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/myservlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 1L;

    @Reference
    StudentClassService studentClassService;


    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("servlet called");


        int option = Integer.parseInt(req.getParameter("a"));
        int id =Integer.parseInt(req.getParameter("b"));

        resp.getWriter().write("goto http://localhost:4502/bin/servlet2?id=0&name=\"null\"age=0&marks=0" +
                "for adding student"
                +
                "\n 1 for deleting student\n 2 for checking if student passed\n" +
                " 3 for getting student details\n 4 for getting all students\n\n");

        switch (option){
            case 1: studentClassService.deleteStudent(1);
                resp.getWriter().write("Deleting student\n");
                break;
            case 2: boolean result = studentClassService.isStudentPassed(id);
                    if(result){resp.getWriter().write("You PASSED the exam");}
                    else {resp.getWriter().write("You FAILED the exam");}

                    break;
            case 3: student obj = studentClassService.getStudent(id);
                resp.getWriter().write("id"+obj.getId()+"\n");
                resp.getWriter().write("name"+obj.getName()+"\n");
                resp.getWriter().write("marks"+obj.getMarks_obtained()+"\n");
                resp.getWriter().write("age"+obj.getAge()+"\n");

                break;
            case 4: resp.getWriter().write(String.valueOf(studentClassService.getAllStudents()));
                break;
            default: resp.getWriter().write("\nenter your query in url...");
        }

        LOGGER.info("Switch case ran.");


    }
}

