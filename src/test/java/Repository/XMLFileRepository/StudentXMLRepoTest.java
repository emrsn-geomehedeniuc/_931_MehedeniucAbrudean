package Repository.XMLFileRepository;

import Domain.Student;
import Exceptions.ValidatorException;
import Service.XMLFileService.StudentXMLService;
import Validator.StudentValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentXMLRepoTest {

    StudentValidator mStudentValidator;
    StudentXMLService mStudentXMLService;
    StudentXMLRepo mStudentXMLRepo;

    @Before
    public void setup() {
        mStudentValidator = new StudentValidator();
        mStudentXMLRepo = new StudentXMLRepo(mStudentValidator, "StudentiXML.xml");
        mStudentXMLService = new StudentXMLService(mStudentXMLRepo);
    }

    @After
    public void tearDown() {
        mStudentXMLRepo = null;
        mStudentXMLService = null;
        mStudentValidator = null;
    }

    @Test
    public void saveStudent1() throws ValidatorException {
        Student student = new Student("1", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
        assertEquals("Andreea Vescan", mStudentXMLRepo.findOne("1").getIndrumator());
    }

    @Test
    public void saveStudent2() throws ValidatorException {
        Student student = new Student("1", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
        assertEquals("Sergiu", mStudentXMLRepo.findOne("1").getNume());

        student.setId("2");
        student.setNume("Geo");
        student.setEmail("geo@geo.com");
        mStudentXMLRepo.save(student);
        assertNotEquals("Sergiu", mStudentXMLRepo.findOne("1").getNume());

        assertEquals("Geo", mStudentXMLRepo.findOne("1").getNume());
    }
}