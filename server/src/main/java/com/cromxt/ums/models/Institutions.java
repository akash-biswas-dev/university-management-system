package com.cromxt.ums.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "institutions")
@AllArgsConstructor
@NoArgsConstructor
public class Institutions {

  @Id
  @Column(name = "institution_name", length = 200)
  private String institutionName;
}
