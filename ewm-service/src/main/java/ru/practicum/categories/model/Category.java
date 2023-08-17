package ru.practicum.categories.model;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(
        name = "categories",
        uniqueConstraints = {
        @UniqueConstraint(name = "category_name_unique", columnNames = "name")}
)
@ToString
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;  // Идентификатор;

    @Column(
            name = "name",
            nullable = false,
            length = 50
    )
    private String name;
}