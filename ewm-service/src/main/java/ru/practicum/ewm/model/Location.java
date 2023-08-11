package ru.practicum.ewm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Location {
    @Column(name = "lat")
    private Float lat;
    @Column(name = "lon")
    private Float lon;
}
