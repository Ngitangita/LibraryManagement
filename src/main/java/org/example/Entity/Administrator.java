package org.example.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Administrator extends User{
    private String role;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    public Administrator(int id, String name, String role, String email, String password, LocalDateTime createdAt){
        super ( id, name );
        this.role = role;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        if (!super.equals ( o )) return false;
        Administrator that = (Administrator) o;
        return Objects.equals ( role, that.role ) && Objects.equals ( email, that.email ) && Objects.equals ( password, that.password ) && Objects.equals ( createdAt, that.createdAt );
    }

    @Override
    public int hashCode(){
        return Objects.hash ( super.hashCode ( ), role, email, password, createdAt );
    }

    @Override
    public String toString(){
        return "Administrator{" +
                "role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
