package org.example.Repository;

import org.example.Entity.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorCrudOperations implements CrudOperations <Administrator> {
    private Connection connection;
    public AdministratorCrudOperations(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Administrator> findAll(){
        List<Administrator> administrators = new ArrayList<> ();
        String sql = "SELECT * FROM \"administrator\"";

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Administrator administrator = new Administrator (
                    resultSet.getInt ( "id"),
                    resultSet.getString ("name"),
                    resultSet.getString ( "role"),
                    resultSet.getString ( "email"),
                    resultSet.getString ( "password"),
                    resultSet.getTimestamp("created_at").toLocalDateTime()
                    );
                    administrators.add ( administrator );
                }
            }
        } catch ( SQLException e ) {
            throw new RuntimeException ( e );
        }

        return administrators;
    }

    @Override
    public List<Administrator> saveAll(List<Administrator> toSave){
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO \"administrator\" (name, role, email, password, created_at) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Administrator administrator : toSave) {
                    preparedStatement.setString(1, administrator.getName ( ) );
                    preparedStatement.setString(2, administrator.getRole ( ) );
                    preparedStatement.setString ( 3, administrator.getEmail ( ) );
                    preparedStatement.setString ( 4, administrator.getPassword ( ) );
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(administrator.getCreatedAt()));
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Administrator save(Administrator toSave){
        try {
            String sql = "INSERT INTO \"administrator\" (name, role, email, password, created_at) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, toSave.getName ());
                preparedStatement.setString(2, toSave.getRole ());
                preparedStatement.setString ( 3, toSave.getEmail () );
                preparedStatement.setString ( 4, toSave.getPassword ( ));
                preparedStatement.setTimestamp(5, Timestamp.valueOf(toSave.getCreatedAt()));

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            toSave.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public Administrator delete(Administrator toDelete){
        try {
            String sql = "DELETE FROM \"administrator\" WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, toDelete.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
