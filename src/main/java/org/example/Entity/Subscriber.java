package org.example.Entity;

import java.util.List;
import java.util.Objects;

public class Subscriber extends User{
    private String reference;
    private List<Borrowing> borrowings;

    public Subscriber(long id, String name, String reference, List<Borrowing> borrowings) {
        super( (int) id, name);
        this.reference = reference;
        this.borrowings = borrowings;
    }

    public void addBorrowing(Borrowing borrowing) {
        borrowings.add(borrowing);
        borrowing.setSubscriber(this);
    }

    public void removeBorrowing(Borrowing borrowing) {
        borrowings.remove(borrowing);
        borrowing.setSubscriber(null);
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber that)) return false;
        return Objects.equals(reference, that.reference) && Objects.equals(borrowings, that.borrowings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, borrowings);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "reference='" + reference + '\'' +
                ", borrowings=" + borrowings +
                super.toString() +
                '}';
    }
}
