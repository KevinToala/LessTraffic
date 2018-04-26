package com.lesstraffic.queueconsumer.repositories;

import com.lesstraffic.queueconsumer.entities.Event;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface EventRepository extends Repository<Event, Long> {
    Stream<Event> findAll();
    Optional<Event> findByTopic(String topic);
}
