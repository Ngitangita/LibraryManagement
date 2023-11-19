package org.example.Entity;

import java.util.Objects;

public class Subscriber extends User{
    private String reference;

    public Subscriber(){
        super ( id, name );
        this.reference = reference;
    }

    public String getReference(){
        return reference;
    }

    public void setReference(String reference){
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        if (!super.equals ( o )) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals ( reference, that.reference );
    }

    @Override
    public int hashCode(){
        return Objects.hash ( super.hashCode ( ), reference );
    }

    @Override
    public String toString(){
        return "Subscriber{" +
                "reference='" + reference + '\'' +
                '}';
    }
}
