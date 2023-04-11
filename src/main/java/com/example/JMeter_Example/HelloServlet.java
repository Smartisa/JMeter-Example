package com.example.JMeter_Example;

import java.io.*;
import javax.servlet.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        String rootPath = context.getRealPath("/");

        String fileName = "output.txt";
        String content = request.getParameter("inputValue");
        String message = "";
        try {
            FileWriter fileWriter = new FileWriter(new File(rootPath, fileName), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
            message = "已将一行文字保存到txt文件中。";
        } catch (IOException e) {
            message = e.getMessage();
        }

        String url = "index.jsp";
        String script = "<script>alert('" + message + "');window.location.href='" + url + "';</script>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(script);
    }

    public void destroy() {
    }
}