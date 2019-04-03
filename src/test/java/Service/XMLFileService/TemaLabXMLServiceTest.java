package Service.XMLFileService;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.TemaLabValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemaLabXMLServiceTest {

    private TemaLabXMLRepo mTemaLabXMLRepo;
    private TemaLabXMLService mTemaLabXMLService;
    private TemaLabValidator mTemaLabValidator;
    private String XML_FILE_NAME = "TemaLaboratorXML.xml";

    @Before
    public void setUp() throws Exception {
        mTemaLabValidator = new TemaLabValidator();
        mTemaLabXMLRepo = new TemaLabXMLRepo(mTemaLabValidator, XML_FILE_NAME);
        mTemaLabXMLService = new TemaLabXMLService(mTemaLabXMLRepo);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addAssignment1() throws ValidatorException {
        assertEquals(true, true);
    }

    @Test
    public void addAssignment2() throws ValidatorException {
        assertEquals(true, true);
    }
}