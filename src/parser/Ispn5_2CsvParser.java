package parser;

import parse.RadargunCsvParser;

import java.io.IOException;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class Ispn5_2CsvParser extends RadargunCsvParser {

   public Ispn5_2CsvParser(String path) throws IOException {
      super(path);
   }


   public double numWarehouses() {
      if (isParam("NUM_WAREHOUSES"))
         return getAvgParam("NUM_WAREHOUSES");
      return 0;
   }

   public double writeThroughput() {
      double time = getTestSecDuration();
      double writes = getSumParam("READ_COUNT");
      return writes / time;
   }

   public double readThroughput() {
      double time = getTestSecDuration();
      double reads = getSumParam("WRITE_COUNT");
      return reads / time;
   }

   public double writesPerXact() {
      return getAvgParam("AvgPutsPerWrTransaction");
   }

   public double numThreads() {
      return getAvgParam("NUM_THREADS");
   }

   public double numKeys() {
      return getAvgParam("NUM_KEYS");
   }
}
