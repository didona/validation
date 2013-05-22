package validations;

import common.AbstractValidator;
import common.NotValidatedException;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class SimpleValidator extends AbstractValidator<Ispn5_2CsvParser>{

   @Override
   public void validate(Ispn5_2CsvParser parser) throws NotValidatedException {
      SimpleValidatedScenario validatedScenario = new SimpleValidatedScenario(parser);
   }

   private double readOnlyTotalR(Ispn5_2CsvParser parser){
      double local =
   }

   private double updateTotalR(Ispn5_2CsvParser parser){

   }
}
