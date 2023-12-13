package com.example.aftas.repository;

import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.number = :number")
    List<Member> findByNumber(@Param("number") Long number);
    @Query("SELECT m FROM Member m WHERE m.firstName = :firstName OR m.lastName = :lastName")
    List<Member> findByFirstNameOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
