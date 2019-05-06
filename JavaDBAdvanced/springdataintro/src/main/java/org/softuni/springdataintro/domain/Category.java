package org.softuni.springdataintro.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    private int id;
    private String name;
    private Set<Book> books;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Book.class, mappedBy = "categories")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
