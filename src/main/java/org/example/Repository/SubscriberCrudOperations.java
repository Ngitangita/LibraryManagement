package org.example.Repository;

import org.example.Entity.Subscriber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberCrudOperations implements CrudOperations <Subscriber> {
    private Connection connection;
    public SubscriberCrudOperations(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Subscriber> findAll(){
        List<Subscriber> subscribers = new ArrayList<> ();
        String sql = "SELECT * FROM \"subscriber\"";

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Subscriber subscriber = new Subscriber (  );
                    subscriber.setId ( resultSet.getInt ( "id" ) );
                    subscriber.setName ( resultSet.getString ( "name") );
                    subscriber.setReference ( resultSet.getString ( "reference") );
                    subscribers.add(subscriber);
                }
            }
        } catch ( SQLException e ) {
            throw new RuntimeException ( e );
        }

        return subscribers;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave){
        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO \"subscriber\" (name, reference) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Subscriber subscriber : toSave) {
                    preparedStatement.setString(1, subscriber.getName ( ) );
                    preparedStatement.setString(2, subscriber.getReference ());
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
    public Subscriber save(Subscriber toSave){
        try {
            String sql = "INSERT INTO \"subscriber\" (name, reference) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, toSave.getName ());
                preparedStatement.setString(2, toSave.getReference ());

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
    public Subscriber delete(Subscriber toDelete){
        try {
            String query = "DELETE FROM \"subscriber\" WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, toDelete.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
