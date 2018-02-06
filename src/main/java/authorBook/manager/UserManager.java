package authorBook.manager;

import authorBook.db.DBConnectionProvider;
import authorBook.model.Gender;
import authorBook.model.User;

import java.sql.*;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addUser(User user) {

        try {
            String query = "insert into `user`(`name`,`surname`,`email`,`gender`,`password`)" +
                    "values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getGender().name());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("User not added", e);
        }
    }


    public User getUserByEmailAndPasswor(String email, String password) {
        String query = "select * from `user` where `email`=? and `password`=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setGender(Gender.valueOf(resultSet.getString("gender")));
                user.setEmail(resultSet.getString("email"));
//                user.setPassword(resultSet.getString("password"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("failed to retrieve user from DB", e);
        }
    }

    public boolean isEmailExist(String email) {
        String query = "select count(*) from user where email='" + email + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            } else return false;
        } catch (SQLException e) {
            throw new RuntimeException("failed to check email in Db", e);
        }
    }
}
