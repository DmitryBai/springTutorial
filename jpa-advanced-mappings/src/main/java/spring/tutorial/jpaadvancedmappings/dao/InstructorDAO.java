package spring.tutorial.jpaadvancedmappings.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.tutorial.jpaadvancedmappings.entity.Instructor;

public interface InstructorDAO extends JpaRepository<Instructor, Integer> {

}
