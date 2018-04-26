package com.lesstraffic.queueconsumer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "events")

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {
    @Column(name = "topic", nullable = false, unique = true)
    private String topic;
    private String description;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    @OrderBy("ordinal asc")
    private List<Action> actions;
}