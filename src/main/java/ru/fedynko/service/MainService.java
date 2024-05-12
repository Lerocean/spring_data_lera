package ru.fedynko.service;

import java.sql.Types;
import java.util.List;
import ru.fedynko.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final JdbcTemplate jdbcTemplate;

    public MainService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void doTestOperations() {
        createUserTable();
        createUser(new User("Lera", "Fedynko", 18));
        getAllUsers().forEach(System.out::println);
        updateUser(new User("Lera", "Fedynko", 19));
        System.out.println("Table updated");
        deleteUser(new User("Lera", "Fedynko", 19));
        System.out.println("Table is empty");
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String name = rs.getString("name");
            String family = rs.getString("family");
            int age = rs.getInt("age");
            return new User(name, family, age);
        });
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (name, family, age) VALUES (?,?,?)";
        Object[] params = {user.getName(), user.getFamily(), user.getAge()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        jdbcTemplate.update(sql, params, types);
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET age = ? WHERE name = ? AND family = ?";
        Object[] params = {user.getAge(), user.getName(), user.getFamily()};
        int[] types = {Types.INTEGER, Types.VARCHAR, Types.VARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

    public void deleteUser(User user) {
        String sql = "DELETE FROM users WHERE name = ? AND family = ?";
        Object[] params = {user.getName(), user.getFamily()};
        int[] types = {Types.VARCHAR, Types.VARCHAR};
        jdbcTemplate.update(sql, params, types);
    }

    private void createUserTable() {
        String sql = "CREATE TABLE Users (" +
                "    name VARCHAR(255)," +
                "    family VARCHAR(255)," +
                "    age INT" +
                ")";
        jdbcTemplate.execute(sql);
        System.out.println("Table Users created successfully.");
    }
}