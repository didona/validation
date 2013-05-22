package common;

import parse.RadargunCsvParser;

import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public interface Validator<T extends RadargunCsvParser> {

   public void validate(T parser) throws NotValidatedException;
   public List<ValidatedScenario<T>> getValidatedScenarios();
}
