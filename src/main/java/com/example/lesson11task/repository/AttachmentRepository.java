package com.example.lesson11task.repository;

import com.example.lesson11task.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
