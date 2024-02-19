package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHtmlServlet",urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Make Study. 19강 HTTP 응답 메세지 (단순 텍스트, HTML)
         * */

        /// Test : urlPatterns

        /// HTTP 응답으로 HTML 반환할 때에는 ContentType을 "text/html"으로 지정해야 한다.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<b>안녕하신가</b>");
        writer.println("</body>");
        writer.println("</html>");

    }
}
