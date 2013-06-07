package gmu;

import common.ValidatedScenario;
import ispn_53.output.ISPN_53_D_TPC_GMU_Result;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuValidatedScenario extends ValidatedScenario<Ispn5_2CsvParser> {

   private ISPN_53_D_TPC_GMU_Result result;

   public GmuValidatedScenario(Ispn5_2CsvParser relevantCsv, ISPN_53_D_TPC_GMU_Result result) {
      super(relevantCsv);
      this.result = result;
   }


   public double predThroughput(){
      return result.throughput();
   }

   public double predThroughputR(){
      return result.roThroughput();
   }

   public double predThroughputW(){
      return result.wrThroughput();
   }
}
