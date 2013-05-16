package printers;

import main.GlobalValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import validations.ValidatedScenario;

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
public abstract class ValidationPrinter {

   private List<ValidatedScenario> validatedScenarios;
   private FileWriter fw;
   private String sep = ";";
   private final static DecimalFormat dcf = new DecimalFormat("###,###.########");
   private final static Log log = LogFactory.getLog(ValidationPrinter.class);

   private StringBuilder sb = new StringBuilder();

   public ValidationPrinter(String testPath, String output, List<ValidatedScenario> validatedScenarios) {
      String outputt = testPath + output;
      try {
         this.fw = new FileWriter(new File(outputt));
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }
      this.validatedScenarios = validatedScenarios;
   }

   public final void printValidation() {
      this.writeAndCarry(header());
      for (ValidatedScenario v : validatedScenarios) {
         this.writeAndCarry(line(v));
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


   private void flush(){
      sb = new StringBuilder();
   }

   protected final void put(String s){
      appendAndSep(sb,s);
   }

   protected final void put(double d){
      appendAndSep(sb,d);
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


   protected abstract String header();

   protected abstract String line(ValidatedScenario vs);
}
