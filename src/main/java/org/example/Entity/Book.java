package org.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private int id;
    private String bookName;
    private int pageNumber;
    private LocalDateTime releaseDate;
    private Author author;

    public Book(){
        this.id = id;
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.releaseDate = releaseDate;
        this.author = author;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getBookName(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public int getPageNumber(){
        return pageNumber;
    }

    public void setPageNumber(int pageNumber){
        this.pageNumber = pageNumber;
    }

    public Date getReleaseDate(){
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
    }

    public Author getAuthor(){
        return author;
    }

    public void setAuthor(int author){
        this.author = author;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        Book book = (Book) o;
        return id == book.id && pageNumber == book.pageNumber && Objects.equals ( bookName, book.bookName ) && Objects.equals ( releaseDate, book.releaseDate ) && Objects.equals ( author, book.author );
    }

    @Override
    public int hashCode(){
        return Objects.hash ( id, bookName, pageNumber, releaseDate, author );
    }

    @Override
    public String toString(){
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", pageNumber=" + pageNumber +
                ", releaseDate=" + releaseDate +
                ", author=" + author +
                '}';
    }

    public void setName(String bookName){
    }
}
