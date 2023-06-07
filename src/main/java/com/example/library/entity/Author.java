package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "first-name", nullable = false)
    String firstName;

    @Column(name = "last-name", nullable = false)
    String lastName;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books = new ArrayList<>();

}
