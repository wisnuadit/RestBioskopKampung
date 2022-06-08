package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Studios")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudioID")
    private Integer studioId;

    @Column(name = "StudioNumber")
    private Integer studioNumber;

    @Column(name = "[Description]")
    private String description;

    @Column(name = "IsEnabled")
    private boolean isEnabled;

    @JsonIgnore
    @OneToMany(mappedBy = "studio")
    private List<Seat> seats;

    @JsonIgnore
    @OneToMany(mappedBy = "studio")
    private List<Schedule> schedules;

    public Studio(Integer studioNumber, String description) {
        this.studioNumber = studioNumber;
        this.description = description;
    }
}
