package authorBook.servlet;

import authorBook.manager.BookManager;
import authorBook.manager.UserManager;
import authorBook.model.User;
import authorBook.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String errMessage = "";
        if (Validator.isEmpty(email)) {
            errMessage += "Email is empty<br>";
        }
        if (Validator.isEmpty(password)) {
            errMessage += "Password is empty<br>";
        }


        UserManager userManager = new UserManager();
        BookManager bookManager = new BookManager();
        if (errMessage.equals("")) {
           User user = userManager.getUserByEmailAndPasswor(email, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                req.setAttribute("userBooks", bookManager.getBooksByUserId(user.getId()));
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            } else {
                errMessage = "invalid login or password";
                req.setAttribute("errMessage", errMessage);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errMessage", errMessage);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }


}
