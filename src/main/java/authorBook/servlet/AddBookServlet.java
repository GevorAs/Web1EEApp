package authorBook.servlet;

import authorBook.manager.BookManager;
import authorBook.model.Book;
import authorBook.model.User;
import authorBook.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        String errMessage = "";
        BookManager bookManager = new BookManager();
        if (Validator.isEmpty(name)) {
            errMessage += "Name is empty<br>";
        }

        if (Validator.isEmpty(author)) {
            errMessage += "Author is empty<br>";
        }
        if (Validator.isEmpty(price)) {
            errMessage += "Price is empty<br>";
        }
        if (Validator.isEmpty(description)) {
            errMessage += "Description is empty<br>";
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (errMessage.equals("")) {
            Book book = new Book(name, author, Double.parseDouble(price), description, user.getId());
            bookManager.addBook(book);
            req.setAttribute("info", "Book is added");
            req.setAttribute("userBooks", bookManager.getBooksByUserId(user.getId()));
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            req.setAttribute("userBooks", bookManager.getBooksByUserId(user.getId()));
            req.setAttribute("errMessage", errMessage);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }
}
