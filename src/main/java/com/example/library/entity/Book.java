package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE book SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "parution", nullable = false)
    Date parution;

    @Column(name = "pages", nullable = false)
    short pages;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;


    @ManyToMany
    @JoinTable(name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Collection<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id"))
    private Collection<Author> authors = new ArrayList<>();

    @Column(name = "deleted")
    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

