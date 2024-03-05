package spring.tutorial.jpaadvancedmappings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.tutorial.jpaadvancedmappings.dao.CustomDAO;
import spring.tutorial.jpaadvancedmappings.dao.InstructorDAO;
import spring.tutorial.jpaadvancedmappings.entity.Course;
import spring.tutorial.jpaadvancedmappings.entity.Instructor;
import spring.tutorial.jpaadvancedmappings.entity.InstructorDetail;
import spring.tutorial.jpaadvancedmappings.entity.Review;
import spring.tutorial.jpaadvancedmappings.entity.Student;

import java.util.List;

@SpringBootApplication
public class JpaAdvancedMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAdvancedMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO,
											   CustomDAO customDAO
											   ) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCourses(instructorDAO, customDAO);
			//findInstructorJoinFetch(customDAO);
			//updateInstructor(instructorDAO);
			//updateCourse(customDAO);
			//deleteInstructor(customDAO);
			//deleteCourse(customDAO);
			//createCourseAndReviews(customDAO);
			//findCourseWithReviews(customDAO);
			//deleteCourse(customDAO);
			//createCourseAndStudents(customDAO);
			findCourseAndStudents(customDAO);
		};
	}

	private void createInstructor(InstructorDAO instructorDAO) {
		Instructor instructor = new Instructor("Chad", "Derby", "darby@luv2code.com");

		InstructorDetail instructorDetail = new InstructorDetail("youtube.com/luv2code", "Coding");

		Course course = new Course("Spring 5 - Advanced course");
		Course course1 = new Course("Java for beginners");

		instructor.addCourse(course);
		instructor.addCourse(course1);

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving: " + instructor);
		instructorDAO.save(instructor);
	}

	private void findInstructor(InstructorDAO instructorDAO) {
		System.out.println(instructorDAO.findById(1).get());
	}

	private void deleteInstructor(InstructorDAO instructorDAO) {
		instructorDAO.delete(instructorDAO.findById(2).get());
	}

	private void findInstructorDetail(InstructorDAO instructorDAO) {
	}

	private void findInstructorWithCourses(InstructorDAO instructorDAO) {
		System.out.println(instructorDAO.findById(1));
	}

	// For LAZY Fetch type where we can't just fetch Course during fetch of Instructor
	private void findCourses(InstructorDAO instructorDAO, CustomDAO customDAO) {
		Instructor instructor = instructorDAO.findById(1).get();

		List<Course> coursesByInstructorId = customDAO.findCoursesByInstructorId(1);

		instructor.setCourses(coursesByInstructorId);

		System.out.println(instructor);
	}

	/**
	 * Similar to EAGER Fetch but more explicit.
	 * Results in 2 queries which can be brought down to 1
	 * if "JOIN FETCH" will be added to get InstructorDetail
	 */
	private void findInstructorJoinFetch(CustomDAO customDAO) {
		System.out.println(customDAO.findInstructorByIdJoinFetch(1));
	}

	private void updateInstructor(InstructorDAO instructorDAO) {
		var instructor = instructorDAO.findById(1).get();

		instructor.setLastName("Morgan");

		instructorDAO.save(instructor);
	}

	private void updateCourse(CustomDAO customDAO) {
		int id = 10;

		Course course = customDAO.findCourseById(id);

		course.setTitle("New Title for Course");

		customDAO.updateCource(course);
	}

	private void deleteInstructor(CustomDAO customDAO) {
		customDAO.deleteInstructorById(1);
	}

	private void deleteCourse(CustomDAO customDAO) {
		customDAO.deleteCourseById(10);
	}

	private void createCourseAndReviews(CustomDAO customDAO) {
		Course course = new Course("Pacman - How to score 1 mil points");

		course.addReview(new Review("Nice"));
		course.addReview(new Review("Cool!"));
		course.addReview(new Review("nothing special..."));

		customDAO.saveCourse(course);
	}
	private void findCourseWithReviews(CustomDAO customDAO) {
		System.out.println(customDAO.findCourseAndReviewsJoinFetch(10));
	}

	private void createCourseAndStudents(CustomDAO customDAO) {
		Course course = new Course("Pacman - How to score 1 mil points");

		Student student = new Student("John", "Doe", "john@luv2code.com");
		Student student1 = new Student("Mary", "Public", "mary@luv2code.com");

		course.addStudent(student);
		course.addStudent(student1);

		customDAO.saveCourse(course);
	}

	private void findCourseAndStudents(CustomDAO customDAO) {
		System.out.println(customDAO.findCourseAndStudentsByCourseId(10));
	}
}
