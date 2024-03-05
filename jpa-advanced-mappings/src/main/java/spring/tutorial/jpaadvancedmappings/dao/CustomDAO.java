package spring.tutorial.jpaadvancedmappings.dao;

import spring.tutorial.jpaadvancedmappings.entity.Course;
import spring.tutorial.jpaadvancedmappings.entity.Instructor;

import java.util.List;

public interface CustomDAO {

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateCource(Course course);

    Course findCourseById(int id);

    void deleteInstructorById(int id);

    void deleteCourseById(int id);

    void saveCourse(Course course);

    Course findCourseAndReviewsJoinFetch(int id);

    Course findCourseAndStudentsByCourseId(int id);
}
