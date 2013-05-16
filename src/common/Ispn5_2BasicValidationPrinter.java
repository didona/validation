package common;

import parser.Ispn5_2CsvParser;
import printers.ValidationPrinter;
import validations.ValidatedScenario;

import java.util.List;


/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 04/10/12
 */
public abstract class Ispn5_2BasicValidationPrinter extends ValidationPrinter {


   public Ispn5_2BasicValidationPrinter(String outpath, List<ValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   protected final void basicHeader(StringBuilder sb) {
      appendAndSep(sb, "Numkeys");
      appendAndSep(sb, "NumWrites");
      appendAndSep(sb, "NumThread");
      appendAndSep(sb, "NumNodes");
      appendAndSep(sb, "Warehouses");
      appendAndSep(sb, "Parallelism");
      appendAndSep(sb, "Throughput");
      appendAndSep(sb, "WriteThroughput");
      appendAndSep(sb, "ReadThroughput");

   }

   protected final void printBasicStats(ValidatedScenario<Ispn5_2CsvParser> validatedScenario) {
      Ispn5_2CsvParser csvParser = validatedScenario.getRelevantCsv();
      double keys = csvParser.numKeys();
      put(keys);
      double writes = csvParser.writesPerXact();
      put(writes);
      double threads = csvParser.numThreads();
      put(threads);
      double nodes = csvParser.getNumNodes();
      put(nodes);
      put(csvParser.numWarehouses());
      put(nodes * threads);
      double writeT = csvParser.writeThroughput();
      double readT = csvParser.readThroughput();
      put(writeT + readT);
      put(writeT);
      put(readT);
   }

}
