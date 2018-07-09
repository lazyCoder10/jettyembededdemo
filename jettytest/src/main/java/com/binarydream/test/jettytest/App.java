package com.binarydream.test.jettytest;

/**
 * Hello world!
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;


public class App
{

        public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.setContextPath("/hello");

        server.setHandler(context);


        context.addServlet(new ServletHolder(new HelloServlet()), "/*");

        FilterHolder filterHolder = new FilterHolder(CrossOriginFilter.class);
        filterHolder.setInitParameter("allowedOrigins", "*");
        filterHolder.setInitParameter("allowedMethods", "GET, POST");
        context.addFilter(filterHolder, "/*", null);

        server.start();

    }

        public static class HelloServlet extends HttpServlet {

            private static final long serialVersionUID = -6154475799000019575L;

            private static final String greeting = "Hello World";

            protected void doGet(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException,
                    IOException {

                response.setContentType("text/html");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println(greeting);
            }

        }
    }

