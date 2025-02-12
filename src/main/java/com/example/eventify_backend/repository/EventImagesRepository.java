package com.example.eventify_backend.repository;

import com.example.eventify_backend.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventImagesRepository extends JpaRepository <EventImage,Long> {
    List<EventImage> findByEventId(Long eventId);
}
