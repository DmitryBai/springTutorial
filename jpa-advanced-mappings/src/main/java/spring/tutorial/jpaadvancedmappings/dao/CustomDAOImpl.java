package spring.tutorial.jpaadvancedmappings.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.tutorial.jpaadvancedmappings.entity.Course;
import spring.tutorial.jpaadvancedmappings.entity.Instructor;

import java.util.List;

@Repository
public class CustomDAOImpl implements CustomDAO {

    private EntityManager entityManager;

    @Autowired
    public CustomDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "from Instructor i join fetch i.courses where i.id = :data", Instructor.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateCource(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        instructor.getCourses().forEach(course -> course.setInstructor(null));

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public Course findCourseAndReviewsJoinFetch(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :data",
                Course.class
        );

        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    @Transactional
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.students WHERE c.id = :data",
                Course.class
        );

        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }
}
