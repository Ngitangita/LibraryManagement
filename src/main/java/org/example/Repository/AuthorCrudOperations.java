package org.example.Repository;

import org.example.Entity.Author;
import org.example.Entity.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations <Author>{
    private Connection connection;

    public AuthorCrudOperations(){
        this.connection = connection;
    }

    @Override
    public List<Author> findAll(){
        List<Author> authors = new ArrayList<> ();
        try {
            String query = "SELECT * FROM author";
            try (Statement statement = connection.createStatement ( )){
                try (ResultSet resultSet = statement.executeQuery ( query )){
                    while (resultSet.next ()) {
                        Author author = new Author (  );
                        author.setId(resultSet.getInt ( "id" ));
                        author.setName (resultSet.getString ( "name" ));
                        author.setSex ( Sex.valueOf ( resultSet.getString ( "sex" ) ) );
                        authors.add ( author );
                    }

                }
            } catch ( SQLException e ) {
                throw new RuntimeException ( e );
            }
        } catch ( RuntimeException e ) {
            throw new RuntimeException ( e );
        }
        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave){
        try {
            connection.setAutoCommit ( false );
            String query = "INSERT INTO author(name, sex) VALUES ( ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement ( query)){
                for (Author author:toSave){
                    preparedStatement.setString ( 1, author.getName ( ) );
                    preparedStatement.setString ( 2, author.getSex ( ) );
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
    public Author save(Author toSave){
        try {
            String query = "INSERT INTO author( name, sex) VALUES ( ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement ( query, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setString ( 1, toSave.getName () );
                preparedStatement.setString ( 2, toSave.getSex () );

                int affectedRows = preparedStatement.executeUpdate ( );
                if (affectedRows > 0){
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys ( )){
                        if (generatedKeys.next ()){
                            toSave.setId ( generatedKeys.getInt ( 1) );
                        }

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
    public Author delete(Author toDelete){
        try {
            String query = "DELETE FROM author WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement ( query)){
                preparedStatement.setInt ( 1, toDelete.getId () );
                preparedStatement.executeUpdate ();
            }
        } catch ( SQLException e ) {
            throw new RuntimeException ( e );
        }
        return toDelete;
    }
}
