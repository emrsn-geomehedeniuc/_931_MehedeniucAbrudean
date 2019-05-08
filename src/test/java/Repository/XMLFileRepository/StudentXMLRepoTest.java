package Repository.XMLFileRepository;

import Domain.Student;
import Exceptions.ValidatorException;
import Service.XMLFileService.StudentXMLService;
import Validator.StudentValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

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
//        mStudentXMLRepo.findAll().forEach(student -> mStudentXMLRepo.delete(student.getId()));
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
        assertEquals("Sergiu", mStudentXMLRepo.findOne("1").getNume());
        assertNotEquals("Geo", mStudentXMLRepo.findOne("1").getNume());
    }


    @Test(expected = ValidatorException.class)
    public void saveStudent4() throws ValidatorException {
        Student student = new Student("a", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent5() throws ValidatorException {
        Student student = new Student(null, "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent6() throws ValidatorException {
        Student student = new Student("1", null, 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent7() throws ValidatorException {
        Student student = new Student("1", "Gheorghe", 0, "Gheorghe@yahoo.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent8() throws ValidatorException {
        Student student = new Student("1", "Gheorghe", 931, "", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent9() throws ValidatorException {
        Student student = new Student("1", "Gheorghe", 931, null, "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent10() throws ValidatorException {
        Student student = new Student("1", "Gheorghe", 931, "gheorghe@gheorghe.com", "");
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent11() throws ValidatorException {
        Student student = new Student("1", "Gheorghe", 931, "gheorghe@gheorghe.com", null);
        mStudentXMLRepo.save(student);
    }

    @Test(expected = ValidatorException.class)
    public void saveStudent12() throws ValidatorException {
        Student student = new Student("1", "sergiu", Integer.MAX_VALUE, "sergiu@sergiu", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }

    @Test
    public void saveStudent13() throws ValidatorException {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE), "Gheorghe", 931, "gheorghe@gheorghe.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
    }
}