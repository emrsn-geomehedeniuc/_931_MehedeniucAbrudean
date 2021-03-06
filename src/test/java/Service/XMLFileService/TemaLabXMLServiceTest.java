package Service.XMLFileService;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Validator.TemaLabValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        String id = "1";
        String descr = "What there is to be done";
        String saptLim = "6";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
        assertEquals(StreamSupport.stream(mTemaLabXMLService.findAll().spliterator(), false).collect(Collectors.toList()).size(), 1);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment2() throws ValidatorException {
        String id = "2";
        String descr = "Descriere Tema";
        String saptLim = "6";
        String saptPred = "15";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment3() throws ValidatorException {
        String id = "0";
        String descr = "Work To Be Done";
        String saptLim = "6";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment4() throws ValidatorException {
        String id = "1";
        String descr = null;
        String saptLim = "6";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment5() throws ValidatorException {
        String id = "1";
        String descr = "";
        String saptLim = "6";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = NumberFormatException.class)
    public void addAssignment6() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = null;
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }


    @Test(expected = NumberFormatException.class)
    public void addAssignment7() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = "6";
        String saptPred = null;

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = NumberFormatException.class)
    public void addAssignment8() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = "";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }


    @Test(expected = NumberFormatException.class)
    public void addAssignment9() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = "6";
        String saptPred = null;

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment10() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = "0";
        String saptPred = "4";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }

    @Test(expected = ValidatorException.class)
    public void addAssignment11() throws ValidatorException {
        String id = "1";
        String descr = "Work To Be Done";
        String saptLim = "6";
        String saptPred = "15";

        String[] params = {id, descr, saptLim, saptPred};
        mTemaLabXMLService.add(params);
    }


}