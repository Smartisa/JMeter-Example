package com.example.JMeter_Example;

import com.DB.DBOperation;
import com.DB.UserConfig;

import java.io.*;
import javax.servlet.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "";

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");

        UserConfig config = new UserConfig();
        try {
            DBOperation operation = new DBOperation(config);
            operation.executeInsert(name, sex, age);

            message = "添加成功";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            message = "添加失败";
        }

        String url = "index.jsp";
        String script = "<script>alert('" + message + "');window.location.href='" + url + "';</script>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(script);
    }

    public void destroy() {
    }
}