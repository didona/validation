package gmu;

import common.Ispn5_2BasicValidationPrinter;
import simpleValidation.SimpleValidatedScenario;

import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuValidatorPrinter extends Ispn5_2BasicValidationPrinter<GmuValidatedScenario> {

   public GmuValidatorPrinter(String outpath, List<GmuValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   @Override
   protected void _header() {
      put("");
      put("Throughput");
      put("RdThroughput");
      put("WrThroughput");
   }

   @Override
   protected void _line(GmuValidatedScenario validatedScenario) {
      put("");
      put(validatedScenario.predThroughput());
      put(validatedScenario.predThroughputR());
      put(validatedScenario.predThroughputW());
   }
}
