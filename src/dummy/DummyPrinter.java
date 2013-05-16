package dummy;

import common.Ispn5_2BasicValidationPrinter;
import validations.ValidatedScenario;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 04/10/12
 */
public class DummyPrinter extends Ispn5_2BasicValidationPrinter {

   public DummyPrinter(String outpath, LinkedList<ValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   @Override
   protected String header() {
      StringBuilder sb = new StringBuilder();
      super.basicHeader(sb);
      return sb.toString();
   }

   @Override
   protected void line(ValidatedScenario validatedScenario) {
      printBasicStats(validatedScenario);
   }
}
