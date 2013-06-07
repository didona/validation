package gmu;

import common.Ispn5_2BasicValidationPrinter;
import simpleValidation.SimpleValidatedScenario;

import java.util.LinkedList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuValidatorPrinter extends Ispn5_2BasicValidationPrinter<GmuValidatedScenario> {

   public GmuValidatorPrinter(String outpath, LinkedList<GmuValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   @Override
   protected void _header() {
      put("");
      put("Throughput");
      put("RdThroughput");
      put("WrThroughput");
      put("RdResponseTime");
      put("WrResponseTime");
      put("WrLocalR");
      put("WrPrepareR");
      put("WrCommitR");
      put("RemoteGetR");
   }

   @Override
   protected void _line(GmuValidatedScenario validatedScenario) {
      put("");
      put(validatedScenario.predThroughput()*1e6);
      put(validatedScenario.predThroughputR()*1e6);
      put(validatedScenario.predThroughputW()*1e6);
      put(validatedScenario.roR());
      put(validatedScenario.wrR());
      put(validatedScenario.updateLocalR());
      put(validatedScenario.updatePrepareR());
      put(validatedScenario.updateCommitR());
      put(validatedScenario.remoteGetR());
   }
}
