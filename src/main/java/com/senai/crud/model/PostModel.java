package com.senai.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity(name = "post_tb")
public class PostModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Size(max=255) @NotNull @NotBlank
    public String title;

    @Size(max = 255) @NotNull @NotBlank
    public String description;

    @CreationTimestamp
    public Instant createdAt;

    @UpdateTimestamp
    public Instant updatedAt;
}
