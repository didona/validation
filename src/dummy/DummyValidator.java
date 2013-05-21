package dummy;


import common.AbstractValidator;
import common.NotValidatedException;
import parse.RadargunCsvParser;
import common.ValidatedScenario;

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
