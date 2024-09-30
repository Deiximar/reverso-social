package com.reversosocial.bean.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "events")
public class Event {

  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;
  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private LocalDate date;
  @Column(nullable = false)
  private LocalTime time;
  @Column(nullable = false)
  private String modality;
  @Column(nullable = false)
  private String location;
  @Column(name = "maximum_participants", nullable = false)
  private String maxParticipants;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sector_id", nullable = false)
  private Sector sector;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
