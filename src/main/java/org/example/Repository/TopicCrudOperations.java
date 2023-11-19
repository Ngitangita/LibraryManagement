package org.example.Repository;

import org.example.Entity.Topic;
import org.example.Entity.TopicName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicCrudOperations implements CrudOperations<Topic> {
    private Connection connection;
    public TopicCrudOperations(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Topic> findAll(){
        List<Topic> topics = new ArrayList<> ();
        try {
            String sql = "SELECT * FROM \"topic\"";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Topic topic = new Topic ();
                        topic.setId ( resultSet.getInt ( "id" ) );
                        topic.setBook ( resultSet.getString ( "book_id" ) );
                        topic.setTopicName ( TopicName.valueOf ( resultSet.getString ( "topic" ) ) );
                        topics.add(topic);
                    }
                } catch ( SQLException e ) {
                    throw new RuntimeException ( e );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    @Override
    public List<Topic> saveAll(List<Topic> toSave){
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO \"topic\" (book_id, topic) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Topic topic : toSave) {
                    preparedStatement.setInt ( 1, topic.getBook () );
                    preparedStatement.setString ( 2, topic.getTopicName () );
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
    public Topic save(Topic toSave){
        try {
            String sql = "INSERT INTO \"topic\" (book_id, topic) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt ( 1, toSave.getBook () );
                preparedStatement.setString ( 2, toSave.getTopicName () );

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
    public Topic delete(Topic toDelete){
        try {
            String query = "DELETE FROM \"topic\" WHERE id = ?";

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
