package Validator;

import Exceptions.ValidatorException;
import Domain.Student;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator implements IValidator<Student> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void validate(Student s) throws ValidatorException {
        String errors = "";
        if (s.getId() == null || (s.getId() != null && s.getId().equals(""))) {
            errors += "Id invalid\n";
        }
        if (s.getId() != null && !StudentValidator.isNumeric(s.getId())) {
            errors += "Id invalid, should be numeric";
        }

        if (s.getNume() == null || s.getNume().equals("")) {
            errors += "Nume invalid\n";
        }
        if (s.getGrupa() <= 0) {
            errors += "Grupa invalid\n";
        }
        if (s.getEmail() == null || s.getEmail().equals("")) {
            errors += "Email invalid\n";
        }
        if (s.getIndrumator() == null || s.getIndrumator().equals("")) {
            errors += "Indrumator invalid\n";
        }
        if (s.getEmail() != null && !validate(s.getEmail())) {
            errors += "Invalid email format";
        }
        if (errors.length() != 0) {
            throw new ValidatorException(errors);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}