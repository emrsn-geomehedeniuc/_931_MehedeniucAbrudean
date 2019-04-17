import Domain.Student;
import Exceptions.ValidatorException;
import Repository.MemoryRepository.AbstractCrudRepo;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.XMLFileService.NotaXMLService;
import Service.XMLFileService.StudentXMLService;
import Service.XMLFileService.TemaLabXMLService;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class BigBangTest {


    private static final String XML_TEMA_LAB = "TemaLaboratorXML.xml";
    private static final String XML_STUDENT = "StudentiXML.xml";
    private static final String XML_NOTA = "NotaXML.xml";

    private TemaLabValidator mTemaLabValidator;
    private TemaLabXMLRepo mTemaLabXMLRepo;
    private TemaLabXMLService mTemaLabXMLService;
    private StudentXMLRepo mStudentXMLRepo;
    private StudentValidator mStudentValidator;
    private StudentXMLService mStudentXMLService;

    private NotaXMLService mNotaXMLService;
    private NotaXMLRepo mNotaXMLRepo;
    private NotaValidator mNotaValidator;

    @Before
    public void setUp() throws Exception {
        mTemaLabValidator = new TemaLabValidator();
        mTemaLabXMLRepo = new TemaLabXMLRepo(mTemaLabValidator, XML_TEMA_LAB);
        mTemaLabXMLService = new TemaLabXMLService(mTemaLabXMLRepo);

        mStudentValidator = new StudentValidator();
        mStudentXMLRepo = new StudentXMLRepo(mStudentValidator, XML_STUDENT);
        mStudentXMLService = new StudentXMLService(mStudentXMLRepo);

        mNotaValidator = new NotaValidator();
        mNotaXMLRepo = new NotaXMLRepo(mNotaValidator, XML_NOTA);
        mNotaXMLService = new NotaXMLService(mNotaXMLRepo);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addStudent() throws ValidatorException {
        Student student = new Student("1", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
        assertEquals("Andreea Vescan", mStudentXMLRepo.findOne("1").getIndrumator());
    }

    @Test
    public void addAssignment() throws ValidatorException {
        String id = "1";
        String descr = "What there is to be done";
        String saptLim = "10";
        String saptPred = "9";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
        assertEquals(StreamSupport.stream(mTemaLabXMLService.findAll().spliterator(), false).collect(Collectors.toList()).size(), 1);
    }

    @Test
    public void addGrade() throws ValidatorException, IOException {

        addStudent();
        addAssignment();

        String id, idStudent, idTema, value, date;
        id = "1";
        idStudent = "1";
        idTema = "1";
        value = "8.5";
        date = "2007-12-03T10:15:30";

        String[] params = {id, idStudent, idTema, value, date};
        mNotaXMLService.add(params);
        assertEquals(StreamSupport.stream(mNotaXMLService.findAll().spliterator(), false).collect(Collectors.toList()).size(), 1);
        mNotaXMLService.printAllNotes(mTemaLabXMLService);
    }
}
