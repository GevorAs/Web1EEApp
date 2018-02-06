package authorBook.servlet;

import authorBook.manager.UserManager;
import authorBook.model.Gender;
import authorBook.model.User;
import authorBook.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String surname=req.getParameter("surname");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String repassword=req.getParameter("repassword");
        String gender=req.getParameter("gender");
        String errMessage="";
        UserManager userManager=new UserManager();

        if (Validator.isEmpty(name)){
            errMessage+="Name is empty<br>";
        }
        if (Validator.isEmpty(surname)){
            errMessage+="Surname is empty<br>";
        }
        if (Validator.isEmpty(email)){
            errMessage+="Email is empty<br>";
        }else if (userManager.isEmailExist(email)){
            errMessage+="Email is already exist<br>";
        }
        if (Validator.isEmpty(password)){
            errMessage+="Password is empty<br>";
        }
        if (password.length()<6){
            errMessage+="Password is small 6<br>";
        }
        if (!password.equals(repassword)){
            errMessage+="Password and Re-password not equals";
        }
        if (errMessage.equals("")) {
            User user=new User(name,surname,email, Gender.valueOf(gender.toUpperCase()),password);
            userManager.addUser(user);
            resp.sendRedirect("index.jsp");
        }else {
            req.setAttribute("errMessage",errMessage);
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
    }
}
