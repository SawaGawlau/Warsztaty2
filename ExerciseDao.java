package ModelDAO;
import Connection.ConnectionUtil;
import Model.Exercise;
import Model.User;

import java.sql.*;
import java.util.Arrays;


public class ExerciseDao {

        private static final String CREATE_EX_QUERY = "INSERT INTO exercise(title) VALUES (?)";
        private static final String READ_EX_QUERY =
                "SELECT * FROM exercise where id = ?";
        private static final String UPDATE_EX_QUERY =
                "UPDATE exercise SET title = ? where id = ?";
        private static final String DELETE_EX_QUERY =
                "DELETE FROM exercise WHERE id = ?";
        private static final String FIND_ALL_EX_QUERY = "SELECT * FROM exercise";

        public Exercise create(Exercise exercise) {
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(CREATE_EX_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, exercise.getTitle());

                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    exercise.setId(resultSet.getInt(1));
                }
                return exercise;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public Exercise read(int exerciseId) {
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(READ_EX_QUERY);
                statement.setInt(1, exerciseId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Exercise exercise = new Exercise();
                    exercise.setId(resultSet.getInt("id"));
                    exercise.setTitle(resultSet.getString("title"));

                    return exercise;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void update(Exercise exercise) {
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_EX_QUERY);
                statement.setString(1, exercise.getTitle());
                statement.setInt(1, exercise.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void delete(int exerciseId) {
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(DELETE_EX_QUERY);
                statement.setInt(1, exerciseId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Exercise [] printAll(){
            try (Connection conn = ConnectionUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(FIND_ALL_EX_QUERY);
                Exercise[] exercises = new Exercise[0];
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Exercise ex = new Exercise();
                    ex.setId(resultSet.getInt("id"));
                    ex.setTitle(resultSet.getString("title"));
                    ex.setDescription(resultSet.getString("description"));
                    exercises = addExToArray(ex, exercises);
                }
                return exercises;
            } catch
            (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    private Exercise [] addExToArray(Exercise ex, Exercise[] exercises) {
        Exercise[] newTabEx = Arrays.copyOf(exercises, exercises.length + 1);
        newTabEx[exercises.length] = ex;
        return newTabEx;
    }


    }
