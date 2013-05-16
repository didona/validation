package common;

import validations.ValidatedScenario;

import java.util.LinkedList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public abstract class AbstractValidator implements Validator{

   protected List<ValidatedScenario> validatedScenarioList = new LinkedList<ValidatedScenario>();

   @Override
   public List<ValidatedScenario> getValidatedScenarios() {
      return this.validatedScenarioList;
   }

   protected void addValidatedScenario(ValidatedScenario v){
      validatedScenarioList.add(v);
   }
}
