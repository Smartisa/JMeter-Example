package com.example.JMeter_Example;

import com.DB.DBOperation;
import com.DB.Student;
import com.DB.UserConfig;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    UserConfig config = new UserConfig();
    DBOperation operation = new DBOperation(config);
    List<Student> studentList = new ArrayList<Student>() {};

    public HelloServlet() throws SQLException {
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String message = "";

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        Student student = new Student(name,sex,age);

        studentList.add(student);

        //TODO
        if(studentList.size()>1000)
        {
            try {
                operation.executeInsert(studentList);
                message = "添加成功";
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                message = "添加失败";
            }

            studentList = new ArrayList<Student>(){};
        }


        String url = "index.jsp";
        String script = "<script>alert('" + message + "');window.location.href='" + url + "';</script>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(script);
    }

    public void destroy() {
    }
}