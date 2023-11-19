package org.example.Repository;

import org.example.Entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations <Book> {
    private Connection connection;
    public BookCrudOperations(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Book> findAll(){
        List<Book> books = new ArrayList<> ();
        try {
            String sql = "SELECT * FROM \"book\"";
            try (Statement statement = connection.createStatement ( )){
                try (ResultSet resultSet = statement.executeQuery ( sql)){
                    while (resultSet.next ()){
                        Book book = new Book ();
                        book.setId ( resultSet.getInt ( "id" ) );
                        book.setBookName ( resultSet.getString ( "book_name" ) );
                        book.setPageNumber ( resultSet.getInt ( "page_numbers") );
                        book.setReleaseDate ( resultSet.getDate ( "releases_date" ) );
                        book.setAuthor ( resultSet.getInt ( "author_id" ) );

                    }
                }
            } catch ( SQLException e ) {
                throw new RuntimeException ( e );
            }
        } catch ( RuntimeException e ) {
            throw new RuntimeException ( e );
        }
        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave){
        try {
            connection.setAutoCommit ( false );
            String sql = "INSERT INTO \"book\"(book_name, page_numbers, releases_date, author_id) VALUES (?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement ( sql)){
                for (Book book:toSave){
                    preparedStatement.setString ( 1, book.getBookName ( ) );
                    preparedStatement.setInt ( 2, book.getPageNumber (  ) );
                    preparedStatement.setDate ( 3, book.getReleaseDate (  ));
                    preparedStatement.setInt ( 4, book.getAuthor ().getId ( ) );

                    preparedStatement.addBatch ();
                }

                preparedStatement.executeBatch ();
                connection.commit ();
            }catch ( SQLException e ) {
                connection.rollback ();
                e.printStackTrace ();
            }finally {
                connection.setAutoCommit ( true );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException ( e );
        }
        return toSave;
    }

    @Override
    public Book save(Book toSave){
        try {
            String sql = "INSERT INTO \"book\"(book_name, page_numbers, releases_date, author_id) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement ( sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString ( 1, toSave.getBookName () );
                preparedStatement.setInt ( 2, toSave.getPageNumber () );
                preparedStatement.setDate ( 3, toSave.getReleaseDate () );
                preparedStatement.setInt ( 4, toSave.getAuthor ().getId ( ) );

                int affectedRows = preparedStatement.executeUpdate ( );
                if (affectedRows > 0){
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys ( )){
                        if ((generatedKeys.next ()))
                            toSave.setId ( generatedKeys.getInt ( 1 ) );
                    }
                }
            } catch ( SQLException e ) {
                throw new RuntimeException ( e );
            }
        } catch ( RuntimeException e ) {
            throw new RuntimeException ( e );
        }
        return toSave;
    }

    @Override
    public Book delete(Book toDelete){
        try {
            String sql = "DELETE FROM \"book\" WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement ( sql )){
                preparedStatement.setInt ( 1, toDelete.getId () );
                preparedStatement.executeUpdate ();
            } catch ( SQLException e ) {
                throw new RuntimeException ( e );
            }
        } catch ( RuntimeException e ) {
            throw new RuntimeException ( e );
        }
        return toDelete;
    }
}
