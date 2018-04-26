package com.lesstraffic.queueconsumer.entities;

import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Entity
@Table(
    name = "actions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "id"})
    }
)

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Action extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "ordinal", nullable = false)
    private short order;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private HttpMethod method;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Lob
    @Column(name = "template", nullable = false)
    private String template;
}