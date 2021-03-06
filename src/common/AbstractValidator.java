package common;

import parse.RadargunCsvParser;

import java.util.LinkedList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public abstract class AbstractValidator<T extends RadargunCsvParser> implements Validator<T>{

   protected List<ValidatedScenario<T>> validatedScenarioList = new LinkedList<ValidatedScenario<T>>();

   @Override
   public final List<ValidatedScenario<T>> getValidatedScenarios() {
      return this.validatedScenarioList;
   }

   public final void addValidatedScenario(ValidatedScenario<T> v){
      validatedScenarioList.add(v);
   }
}
