package com.training.vismainternshiptask.model;

import com.training.vismainternshiptask.enums.Category;
import com.training.vismainternshiptask.enums.Type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "meeting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Meeting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String responsiblePerson;
  private String description;

  @Enumerated(EnumType.STRING)
  private Category category;

  //  @Convert(converter = TypeConverter.class)
  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private Type type;

  private LocalDateTime startDate;
  private LocalDateTime endDate;

  @ManyToMany(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
  }, fetch = FetchType.LAZY)
  @JoinTable(
    name = "meeting_person",
    joinColumns = @JoinColumn(name = "meeting_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id")
  )
  private Set<Person> persons = new HashSet<>();

  public void addPerson(Person person) {
    persons.add(person);
    person.getMeetings().add(this);
  }

  public void removePerson(Person person) {
    persons.remove(person);
    person.getMeetings().remove(this);
  }
}
