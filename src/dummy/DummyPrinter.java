package dummy;

import common.Ispn5_2BasicValidationPrinter;
import validations.ValidatedScenario;

import java.util.List;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyPrinter extends Ispn5_2BasicValidationPrinter {

   public DummyPrinter(String testPath, String output, List<ValidatedScenario> validatedScenarios) {
      super(testPath, output, validatedScenarios);
   }

   @Override
   protected String header() {
      StringBuilder sb = new StringBuilder();
      super.basicHeader(sb);
      return sb.toString();
   }

   @Override
   protected String line(ValidatedScenario validatedScenario) {
      return printBasicStats(validatedScenario);
   }
}
