package dummy;

import common.Ispn5_2BasicValidationPrinter;
import parser.Ispn5_2CsvParser;
import common.ValidatedScenario;

import java.util.LinkedList;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyPrinter extends Ispn5_2BasicValidationPrinter<ValidatedScenario<Ispn5_2CsvParser>> {

   public DummyPrinter(String outpath, LinkedList<ValidatedScenario<Ispn5_2CsvParser>> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   @Override
   protected void _header() {
   }

   @Override
   protected void _line(ValidatedScenario<Ispn5_2CsvParser> validatedScenario) {
   }
}
