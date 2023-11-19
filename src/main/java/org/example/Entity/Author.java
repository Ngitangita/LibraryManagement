package org.example.Entity;

import java.util.Objects;

public class Author extends User{
    private Sex sex;

    public Author(int id, String name, Sex sex){
        super ( id, name );
        this.sex = sex;
    }

    public Author(){
        super ( );
    }

    public String getSex(){
        return sex;
    }

    public void setSex(Sex sex){
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        if (!super.equals ( o )) return false;
        Author author = (Author) o;
        return sex == author.sex;
    }

    @Override
    public int hashCode(){
        return Objects.hash ( super.hashCode ( ), sex );
    }

    @Override
    public String toString(){
        return "Author{" +
                "sex=" + sex +
                '}';
    }
}
