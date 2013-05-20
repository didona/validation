package dummy;

import common.Ispn5_2BasicValidationPrinter;
import parser.Ispn5_2CsvParser;
import validations.ValidatedScenario;

import java.util.LinkedList;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyPrinter extends Ispn5_2BasicValidationPrinter {

   public DummyPrinter(String outpath, LinkedList<ValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   @Override
   protected void header() {
      basicHeader();
   }

   @Override
   protected void line(ValidatedScenario<Ispn5_2CsvParser> validatedScenario) {
      printBasicStats(validatedScenario);
   }
}
