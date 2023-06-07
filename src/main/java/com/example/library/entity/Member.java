package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "first-name", nullable = false)
    String fistName;

    @Column(name = "last-name", nullable = false)
    String lasName;

    @Column(name = "email", nullable = false)
    String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "registered", nullable = false)
    Date registered;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private Collection<Borrow> borrows = new ArrayList<>();

}
