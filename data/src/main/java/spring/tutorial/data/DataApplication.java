package spring.tutorial.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.tutorial.data.dao.StudentDAO;
import spring.tutorial.data.entity.Student;

import java.util.List;

@SpringBootApplication
public class DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args, StudentDAO s) {
		return runner -> {
			//createStudent(s);
			createThreeStudents(s);
			//readStudent(s);
			//queryAllStudents(s);
			//queryStudentsByLastName(s);
			//updateStudent(s);
			//deleteStudent(s);
			//deleteAllStudents(s);
		};
	}

	private void deleteAllStudents(StudentDAO s) {
		System.out.println(s.deleteAll());
	}

	private void deleteStudent(StudentDAO s) {
		s.delete(2);
	}

	private void updateStudent(StudentDAO s) {
		Student student = s.findById(1);
		student.setLastName("Scooby");
		s.update(student);
		System.out.println(student);
	}

	private void queryStudentsByLastName(StudentDAO s) {
		for (Student student: s.findByLastName("Doe")) {
			System.out.println(student);
		}
	}

	private void queryAllStudents(StudentDAO s) {
		for (Student student: s.findAll()) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO s) {
		System.out.println("Found student: " + s.findById(1));
	}

	private void createThreeStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 student objects...");
		Student student1 = new Student("John", "Doe", "paul.doe@mail.com");
		Student student2 = new Student("Mary", "Doe", "paul.doe@mail.com");
		Student student3 = new Student("Joseph", "Doe", "paul.doe@mail.com");

		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student student = new Student("Paul", "Doe", "paul.doe@mail.com");

		System.out.println("Saving the student...");
		studentDAO.save(student);

		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
