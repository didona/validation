package common;

import parse.RadargunCsvParser;
import validations.ValidatedScenario;

import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public interface Validator {

   public void validate(RadargunCsvParser parser) throws NotValidatedException;
   public List<ValidatedScenario> getValidatedScenarios();
}
