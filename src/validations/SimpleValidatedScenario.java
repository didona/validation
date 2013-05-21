package validations;

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
}
