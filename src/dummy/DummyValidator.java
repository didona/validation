package dummy;


import common.AbstractValidator;
import main.NotValidatedException;
import parse.RadargunCsvParser;
import validations.ValidatedScenario;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyValidator extends AbstractValidator {


   @Override
   public void validate(RadargunCsvParser parser) throws NotValidatedException {
      addValidatedScenario(new ValidatedScenario<RadargunCsvParser>(parser));
   }

}
