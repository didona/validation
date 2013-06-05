package gmu;

import parser.Ispn5_2CsvParser;

import java.io.IOException;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuCsvParser extends Ispn5_2CsvParser{

   public GmuCsvParser(String path) throws IOException {
      super(path);
   }

   public double firstWriteIndex(){
      return getAvgParam("NumReadsBeforeWrite");
   }

}
