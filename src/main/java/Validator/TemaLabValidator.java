package Validator;

import Exceptions.ValidatorException;
import Domain.TemaLab;
import Validator.IValidator;

public class TemaLabValidator implements IValidator<TemaLab> {

    public void validate(TemaLab temaLab) throws ValidatorException {
        if (temaLab.getId() == null || temaLab.getId() == 0) {
            throw new ValidatorException("Nr tema invalid\n");
        } else if (temaLab.getDescriere() == null || temaLab.getDescriere().isEmpty()) {
            throw new ValidatorException("Descriere tema invalida\n");
        } else if (temaLab.getSaptammanaPredarii() <= 0 || temaLab.getSaptammanaPredarii() > 14) {
            throw new ValidatorException("Sapatamana predarii invalida\n");
        } else if (temaLab.getTermenLimita() <= 0 || temaLab.getTermenLimita() > 14) {
            throw new ValidatorException("Termen limita invalid\n");
        }
    }
}
