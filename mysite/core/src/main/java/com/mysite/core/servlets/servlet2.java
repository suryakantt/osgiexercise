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

import javax.servlet.Servlet;
import com.mysite.core.services.student;
import javax.servlet.ServletException;

import com.mysite.core.services.impl.StudentClassService;
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
@SlingServletPaths(value = "/bin/servlet2")
public class servlet2 extends SlingSafeMethodsServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 1L;

    @Reference
    StudentClassService Studentclassservice;
    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("servlet called");

        int id = Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        int marks = Integer.parseInt(req.getParameter("marks"));
        int age = Integer.parseInt(req.getParameter("age"));
        if(id!=0) {
            student addit = new student(name, id, age, marks);
            Studentclassservice.addStudent(addit);
            resp.getWriter().write("Student Added");
        }
        resp.getWriter().write(" \n go to http://localhost:4502/bin/myservlet?a=0&b=0 for more options");

    }

}
   
