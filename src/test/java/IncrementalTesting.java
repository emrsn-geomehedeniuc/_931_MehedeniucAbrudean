import Domain.Student;
import Exceptions.ValidatorException;
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

import javax.xml.validation.Validator;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IncrementalTesting {
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
    public void addAssignmentIntegration() throws ValidatorException {
        //Student Test
        Student student = new Student("1", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
        assertEquals("Andreea Vescan", mStudentXMLRepo.findOne("1").getIndrumator());

        //Assignment Test
        String id = "1";
        String descr = "What there is to be done";
        String saptLim = "10";
        String saptPred = "9";
        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
        assertEquals(mTemaLabXMLService.findOne(1).getDescriere(), descr);
        assertEquals(mTemaLabXMLService.findOne(1).getSaptammanaPredarii(), Integer.parseInt(saptPred));
        assertEquals(mTemaLabXMLService.findOne(1).getTermenLimita(), Integer.parseInt(saptLim));
    }

    @Test
    public void addGradeIntegration() throws ValidatorException {
        //Student Test
        Student student = new Student("1", "Sergiu", 931, "sergiu@sergiu.com", "Andreea Vescan");
        mStudentXMLRepo.save(student);
        assertEquals("Andreea Vescan", mStudentXMLRepo.findOne("1").getIndrumator());

        //Assignment Test
        String id = "1";
        String descr = "What there is to be done";
        String saptLim = "10";
        String saptPred = "9";
        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);

        //Grade Test
        String gradeId, gradeIdStudent, gradeIdTema, gradeValue, gradeDate;
        gradeId = "1";
        gradeIdStudent = "1";
        gradeIdTema = "1";
        gradeValue = "8.5";
        gradeDate = "2007-12-03T10:15:30";
        String[] gradeParams = {gradeId, gradeIdStudent, gradeIdTema, gradeValue, gradeDate};
        mNotaXMLService.add(gradeParams);
        assertEquals(mNotaXMLService.findOne(1).getValoare(), 8.5f, 0);
        assertEquals(mNotaXMLService.findOne(1).getLdt(), LocalDateTime.parse(gradeDate));
        assertNotEquals(mNotaXMLService.findOne(1).getValoare(), LocalDateTime.parse("2007-12-03T10:15:31"));

    }
}
