package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrow-start", nullable = false)
    Date borrowStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrow-end", nullable = false)
    Date borrowEnd;

    @Temporal(TemporalType.DATE)
    @Column(name = "returned")
    Date returned;

    @OneToMany(mappedBy = "borrow", orphanRemoval = true)
    private Collection<Book> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
