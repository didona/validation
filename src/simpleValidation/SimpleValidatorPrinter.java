package simpleValidation;

import common.Ispn5_2BasicValidationPrinter;
import common.ValidatedScenario;
import parser.Ispn5_2CsvParser;

import java.util.LinkedList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public final class SimpleValidatorPrinter extends Ispn5_2BasicValidationPrinter<SimpleValidatedScenario>{

   public SimpleValidatorPrinter(String outpath, LinkedList<SimpleValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }


   @Override
   protected void _header() {
      put("");
      put("updateXactTotal");
      put("WaitTimeFromCommit");
   }


   @Override
   protected void _line(SimpleValidatedScenario vs) {
      put("");
   }
}
