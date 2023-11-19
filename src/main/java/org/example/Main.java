package org.example;

import org.example.Repository.AuthorCrudOperations;

public class Main {
    public static void main(String[] args){
        AuthorCrudOperations bcp = new AuthorCrudOperations (  );
        bcp.findAll ();
    }
}
