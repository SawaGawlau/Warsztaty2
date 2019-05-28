package ModelDAO;

import Connection.ConnectionUtil;
import Model.Solution;
import Model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ? , ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL = "SELECT * FROM solution where user_id=?";
    private static final String FIND_ALL_BY_EXC_ID = "SELECT * FROM solution where exercise_id=? group by updated asc";
    private static final String FIND_ALL_USERS = "SELECT * FROM user_group WHERE group_id=?";

    public List<User> findAllByGroupId(int groupId) {
        List<User> userList = new ArrayList<User>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("name"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public List<Solution> findAllByExerciseId(int exerciseId) {
        List<Solution> solutionListByExId = new ArrayList<Solution>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_EXC_ID);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutionListByExId.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutionListByExId;
    }

    public List<Solution> findAllByUserId(int userId) {
        List<Solution> solutionList = new ArrayList<Solution>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutionList.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutionList;
    }


    public Solution create(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
