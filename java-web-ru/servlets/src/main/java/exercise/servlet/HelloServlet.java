package exercise.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Устанавливаем тип содержимого ответа
        resp.setContentType("text/plain");

        // Получаем параметр 'name' из запроса
        String name = req.getParameter("name");

        // Формируем сообщение в зависимости от наличия имени
        String message = (name == null || name.isBlank())
                ? "Hello, Guest!"
                : "Hello, " + name + "!";

        // Отправляем сообщение в ответ
        resp.getWriter().write(message);
    }


}
