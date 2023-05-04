package com.example.JMeter_Example;

import com.DB.DBOperation;
import com.DB.Student;
import com.DB.UserConfig;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    UserConfig config = new UserConfig();
    DBOperation operation = new DBOperation(config);
    final List<Student> studentList = Collections.synchronizedList(new ArrayList<Student>());

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
        synchronized (studentList) {
            studentList.add(student);
            message = insertStudentsIfNeeded(false);
        }

        String url = "index.jsp";
        String script = "<script>alert('" + message + "');window.location.href='" + url + "';</script>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(script);
    }

    private String insertStudentsIfNeeded(boolean forceInsert) {
        String message = "";

        synchronized (studentList) {
            if (studentList.size() >= 100000 || (forceInsert && !studentList.isEmpty())) {
                List<List<Student>> chunks = splitStudentList(studentList, 10000);

                AtomicBoolean success = new AtomicBoolean(true);

                chunks.parallelStream().forEach(chunk -> {
                    try {
                        operation.executeInsert(chunk);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        success.set(false);
                    }
                });

                studentList.clear();
                message = success.get() ? "添加成功" : "添加失败";
            }
        }

        return message;
    }

    private List<List<Student>> splitStudentList(List<Student> studentList, int chunkSize) {
        List<List<Student>> listOfChunks = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i += chunkSize) {
            List<Student> chunk = studentList.subList(i, Math.min(studentList.size(), i + chunkSize));
            listOfChunks.add(chunk);
        }
        return listOfChunks;
    }

    public void destroy() {
        // 销毁时，运行10w以下的数据
        insertStudentsIfNeeded(true);
    }
}