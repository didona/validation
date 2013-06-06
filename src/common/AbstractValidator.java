package common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
   protected final static Log log = LogFactory.getLog(AbstractValidator.class);

   @Override
   public final List<ValidatedScenario<T>> getValidatedScenarios() {
      return this.validatedScenarioList;
   }

   public final void addValidatedScenario(ValidatedScenario<T> v){
      validatedScenarioList.add(v);
   }
}
