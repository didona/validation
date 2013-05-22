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
public final class SimpleValidatorPrinter extends Ispn5_2BasicValidationPrinter<SimpleValidatedScenario> {

   public SimpleValidatorPrinter(String outpath, LinkedList<SimpleValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }


   private double updateXactTotal() {
      double local = currentParser.localResponseTimeWrXact();
      double prepare = currentParser.prepareCommandResponseTime();
      double commit = currentParser.commitCommandResponseTime();
      return local + prepare + commit;
   }

   private double waitTimeFromCommit() {
      double commit = currentParser.commitCommandResponseTime();
      double commitN = currentParser.netAsyncCommit();
      return commit - commitN;
   }

   private double commitFromTotal(){
      double total = currentParser.totalResponseTimeWrXact();
      double local = currentParser.localResponseTimeWrXact();
      double prepare = currentParser.prepareCommandResponseTime();
      return total - (local + prepare);
   }


   @Override
   protected void _header() {
      put("");
      put("updateXactTotal");
      put("WaitTimeFromCommit");
      put("CommitTimeFromTotal");
   }


   @Override
   protected void _line(SimpleValidatedScenario vs) {
      put("");
      put(updateXactTotal());
      put(waitTimeFromCommit());
      put(commitFromTotal());
   }
}
