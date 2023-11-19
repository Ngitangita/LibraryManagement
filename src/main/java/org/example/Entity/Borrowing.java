package org.example.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Borrowing {
    private int id;
    private Subscriber subscriber;
    private Book book;
    private Administrator administrator;
    private LocalDateTime borrowingDate;
    private LocalDateTime returnDate;

    public Borrowing(int id, Subscriber subscriber, Book book, Administrator administrator, LocalDateTime borrowingDate, LocalDateTime returnDate){
        this.id = id;
        this.subscriber = subscriber;
        this.book = book;
        this.administrator = administrator;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Subscriber getSubscriber(){
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber){
        this.subscriber = subscriber;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public Administrator getAdministrator(){
        return administrator;
    }

    public void setAdministrator(Administrator administrator){
        this.administrator = administrator;
    }

    public LocalDateTime getBorrowingDate(){
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDateTime borrowingDate){
        this.borrowingDate = borrowingDate;
    }

    public LocalDateTime getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate){
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        Borrowing borrowing = (Borrowing) o;
        return id == borrowing.id && Objects.equals ( subscriber, borrowing.subscriber ) && Objects.equals ( book, borrowing.book ) && Objects.equals ( administrator, borrowing.administrator ) && Objects.equals ( borrowingDate, borrowing.borrowingDate ) && Objects.equals ( returnDate, borrowing.returnDate );
    }

    @Override
    public int hashCode(){
        return Objects.hash ( id, subscriber, book, administrator, borrowingDate, returnDate );
    }

    @Override
    public String toString(){
        return "Borrowing{" +
                "id=" + id +
                ", subscriber=" + subscriber +
                ", book=" + book +
                ", administrator=" + administrator +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
