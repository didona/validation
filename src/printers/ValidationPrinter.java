package printers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import parse.RadargunCsvParser;
import common.ValidatedScenario;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public abstract class ValidationPrinter <T extends RadargunCsvParser>{

   private List<ValidatedScenario<T>> validatedScenarios;
   private FileWriter fw;
   private final String sep = ";";
   private final static DecimalFormat dcf = new DecimalFormat("###,###.########");
   private final static Log log = LogFactory.getLog(ValidationPrinter.class);

   private StringBuilder sb = new StringBuilder();

   public ValidationPrinter(String outpath, List<ValidatedScenario<T>> validatedScenarios) {
      try {
         this.fw = new FileWriter(new File(outpath));
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }
      this.validatedScenarios = validatedScenarios;
   }


   public final void printValidation() {
      this.writeAndCarry(_header());
      for (ValidatedScenario<T> v : validatedScenarios) {
         this.writeAndCarry(_line(v));
      }
      this.close();
   }


   private void writeAndCarry(String s) {
      try {
         fw.write(s + "\n");
         fw.flush();
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }
   }

   private void close() {
      try {
         this.fw.flush();
         this.fw.close();
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }

   }


   private void flush() {
      sb = new StringBuilder();
   }

   protected final void put(String s) {
      appendAndSep(sb, s);
   }

   protected final void put(double d) {
      appendAndSep(sb, d);
   }

   protected final void appendAndSep(StringBuilder sb, String s) {
      sb.append(s);
      sb.append(sep);
   }


   protected final void appendAndSep(StringBuilder sb, double s) {

      sb.append(dcf.format(s));
      //sb.append(s);
      sb.append(sep);
   }

   protected final void appendAndSep(StringBuilder sb, double... s) {
      for (double d : s)
         appendAndSep(sb, d);
   }

   private String _line(ValidatedScenario<T> vs) {
      line(vs);
      String line = sb.toString();
      flush();
      return line;
   }

   private String _header() {
      header();
      String header = sb.toString();
      flush();
      return header;
   }


   protected abstract void header();

   protected abstract void line(ValidatedScenario<T> vs);
}
