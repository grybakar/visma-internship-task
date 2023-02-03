package com.training.vismainternshiptask.repository;

import com.training.vismainternshiptask.model.Meeting;
import com.training.vismainternshiptask.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
