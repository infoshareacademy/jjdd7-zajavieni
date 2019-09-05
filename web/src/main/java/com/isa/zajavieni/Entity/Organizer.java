package com.isa.zajavieni.Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "organizer")
public class Organizer {

  @Id
  @Column(name = "organizer_id")
  @NotNull
  Long id;

  @Column(name = "designation")
  String designation;

  @OneToMany(mappedBy = "organizer")
  List<Event> events = new ArrayList<>();

  public Organizer(String designation, List<Event> events) {
    this.designation = designation;
    this.events = events;
  }

  public Organizer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }
}
