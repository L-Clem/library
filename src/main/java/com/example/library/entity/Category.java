package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@SQLDelete(sql = "UPDATE category SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "name", nullable = false)
    String name;

    @ManyToMany(mappedBy = "categories")
    private Collection<Book> books = new ArrayList<>();
}
