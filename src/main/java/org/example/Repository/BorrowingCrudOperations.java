package org.example.Repository;

import org.example.Entity.Borrowing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowingCrudOperations implements CrudOperations<Borrowing> {
    private Connection connection;
    public BorrowingCrudOperations(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Borrowing> findAll(){
        List<Borrowing> borrowings = new ArrayList<> ();

        try {
            String sql = "SELECT * FROM \"borrowing\"";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Borrowing borrowing = new Borrowing(
                            resultSet.getInt("id"),
                            resultSet.getInt("book_id"),
                            resultSet.getTimestamp("borrowing_date").toLocalDateTime(),
                            resultSet.getTimestamp("return_date").toLocalDateTime(),
                            resultSet.getInt("administrator_id")
                        );
                        borrowings.add ( borrowing );
                    }
                }
            } catch ( SQLException e ) {
                throw new RuntimeException ( e );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowings;
    }

    @Override
    public List<Borrowing> saveAll(List<Borrowing> toSave){
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO \"borrowing\" (subscriber_id, book_id, borrowing_date, return_date, administrator_id) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Borrowing borrowing : toSave) {
                    preparedStatement.setInt (1, borrowing.getSubscriber ().getId ( ) );
                    preparedStatement.setInt ( 2, borrowing.getBook () );
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(borrowing.getBorrowingDate ()));
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(borrowing.getReturnDate ()));
                    preparedStatement.setInt ( 5, borrowing.getAdministrator ().getId ( ) );
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
    public Borrowing save(Borrowing toSave){
        try {
            String sql = "INSERT INTO \"borrowing\" (subscriber_id, book_id, borrowing_date, return_date, administrator_id) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt (1, toSave.getSubscriber ();
                preparedStatement.setInt ( 2, toSave.getBook ();
                preparedStatement.setTimestamp(3, Timestamp.valueOf(toSave.getBorrowingDate ()));
                preparedStatement.setTimestamp(4, Timestamp.valueOf(toSave.getReturnDate ()));
                preparedStatement.setInt ( 5, toSave.getAdministrator ().getId ( ) );

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
    public Borrowing delete(Borrowing toDelete){
        try {
            String query = "DELETE FROM \"borrowing\" WHERE id = ?";

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
