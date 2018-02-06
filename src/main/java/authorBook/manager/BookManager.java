package authorBook.manager;

import authorBook.db.DBConnectionProvider;
import authorBook.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addBook( Book book) {


        try {
            String query = "insert into `book`(`name`,`author`,`price`,`description`,`user_id`)" +
                    "values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setLong(5,  book.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                book.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("User not added", e);
        }


    }

    public List<Book> getBooksByUserId(long userId) {
        String query="select * from book where user_id='"+userId+"'";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            List<Book> books=new ArrayList<Book>();
            while (resultSet.next()){
                Book book=new Book(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getDouble(4),
                        resultSet.getString(5),resultSet.getInt(6));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrive books by user id",e);
        }
    }
}
