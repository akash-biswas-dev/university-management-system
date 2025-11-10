package com.cromxt.ums.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
  @Id
  private String name;

  private String description;

  @Column(nullable = false, columnDefinition = "DECIMA(10,2)")
  private double amount;
}
