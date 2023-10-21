package dev.brampie.giggleapi.domain.posts;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "post-";
        String uniqueRandomString;

        while (true) {
            uniqueRandomString = generateRandomString(8); // Generate a random string of length 8

            // Check if the generated string is unique in the database
            boolean isUnique = isStringUniqueInDatabase(session, uniqueRandomString);

            if (isUnique) {
                return prefix + uniqueRandomString;
            }
        }
    }

    private String generateRandomString(int length) {
        // Define the characters you want to use in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    private boolean isStringUniqueInDatabase(SharedSessionContractImplementor session, String randomString) {
        try {
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "select count(post_id) from posts where post_id = '" + randomString + "'";

            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // If count is 0, the string is unique
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false; // Default to not unique if there's an error
    }
}
