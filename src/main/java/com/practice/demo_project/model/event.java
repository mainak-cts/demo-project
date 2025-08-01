package com.practice.demo_project.model;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// @Entity
public class event {
    private Long eventId;
    private String name;
    private String category;
    private String location;
    private Date date;
    private User organizer;
    private int availableTickets;
    // @JsonIgnoreProperties
    // @OneToMany(mappedBy="event",cascade=CascadeType.ALL,orphanRemoval=true)
    //

}