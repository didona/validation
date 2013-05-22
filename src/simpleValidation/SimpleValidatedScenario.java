package simpleValidation;

import common.ValidatedScenario;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class SimpleValidatedScenario extends ValidatedScenario<Ispn5_2CsvParser>{

   double updateXactTotalResponseTime;
   double readOnlyXactTotalResponseTime;

   public SimpleValidatedScenario(Ispn5_2CsvParser relevantCsv) {
      super(relevantCsv);
   }


   public double getUpdateXactTotalResponseTime() {
      return updateXactTotalResponseTime;
   }

   public void setUpdateXactTotalResponseTime(double updateXactTotalResponseTime) {
      this.updateXactTotalResponseTime = updateXactTotalResponseTime;
   }

   public double getReadOnlyXactTotalResponseTime() {
      return readOnlyXactTotalResponseTime;
   }

   public void setReadOnlyXactTotalResponseTime(double readOnlyXactTotalResponseTime) {
      this.readOnlyXactTotalResponseTime = readOnlyXactTotalResponseTime;
   }
}
