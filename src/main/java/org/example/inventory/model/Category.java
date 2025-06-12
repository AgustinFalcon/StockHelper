package org.example.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "category")
@Data
public class Category implements Serializable {

    /*
     *
     */
    private static final long serialVersionUID = -4310027227752446841L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}

