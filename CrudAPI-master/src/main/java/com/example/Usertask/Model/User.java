package com.example.Usertask.Model;

import lombok.Data;
import lombok.Getter;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "User_usertask")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "^[a-zA-Z_]+$", message = "Name is invalid")
    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private  String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 10, max = 11, message = "Password must be 10  characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 6, message = "Password must be6 digit")
    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_acitve")
    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
