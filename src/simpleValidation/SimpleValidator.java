package simpleValidation;

import common.AbstractValidator;
import common.NotValidatedException;
import config.validators.SimpleValidatorConfig;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class SimpleValidator extends AbstractValidator<Ispn5_2CsvParser> {

   private SimpleValidatorConfig simpleValidatorConfig;



   @Override
   public void validate(Ispn5_2CsvParser parser) throws NotValidatedException {
      SimpleValidatedScenario validatedScenario = new SimpleValidatedScenario(parser);
      addValidatedScenario(validatedScenario);
   }

   private double readOnlyTotalR(Ispn5_2CsvParser parser) {
      return 0;
   }

   private double updateTotalR(Ispn5_2CsvParser parser) {
      return -2;
   }

   public void setSimpleValidatorConfig(SimpleValidatorConfig simpleValidatorConfig) {
      this.simpleValidatorConfig = simpleValidatorConfig;
   }
}
