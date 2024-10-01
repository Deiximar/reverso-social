package com.reversosocial.bean.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String lastname;
  @Column(nullable = false)
  private LocalDate birthday;
  @Column(nullable = false)
  private String password;
  @Column(unique = true, name = "email", nullable = false)
  private String email;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Event> events;

}