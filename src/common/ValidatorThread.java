package common;

import config.FactoriesConfig;
import config.GlobalValidationConfig;
import factories.ValidatorFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import parse.RadargunCsvParser;
import parser.Ispn5_2CsvParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class ValidatorThread extends Thread {
   private List<ValidatedScenario> validatedScenariosList;
   private List<File> csvFolderList;
   private FactoriesConfig factoriesConfig;
   private GlobalValidationConfig globalValidationConfig;

   private final static Log log = LogFactory.getLog(ValidatorThread.class);

   public ValidatorThread(List<File> l, List<ValidatedScenario> ll, FactoriesConfig fac, GlobalValidationConfig globalValidationConfig) {
      this.csvFolderList = l;
      this.validatedScenariosList = ll;
      this.factoriesConfig = fac;
      this.globalValidationConfig = globalValidationConfig;
   }

   public void run() {

      File folder;
      while ((folder = dequeue(this.csvFolderList)) != null) {

         for (File csv : folder.listFiles()) {
            if (!GlobalValidator.csv(csv))
               continue;
            //if (!processFile(csv))
              // continue;
            System.out.println(csv);
            try {
               //TODO: this is generic in the ValidationResult. This means that every Printer and Validator has to be related to a kind of parser
               RadargunCsvParser parser = new Ispn5_2CsvParser(csv.getPath());
               Validator v = ValidatorFactory.buildValidator(factoriesConfig);
               log.trace("Validating " + csv.getPath());
               v.validate(parser);
               enqueue(this.validatedScenariosList, v);
            } catch (IOException e) {
               e.printStackTrace();
               System.exit(-1);
            } catch (NotValidatedException n) {
               n.printStackTrace();
            }
         }

      }
   }

   private synchronized static File dequeue(List<File> folderList) {
      if (folderList.isEmpty())
         return null;
      File f = folderList.get(0);
      folderList.remove(0);
      log.trace("More " + folderList.size() + " to validate");
      return f;

   }

   private synchronized static void enqueue(List<ValidatedScenario> l, Validator rv) {
      for (ValidatedScenario vs : rv.getValidatedScenarios())
         l.add(vs);
   }

   private static boolean in(double x, double min, double max) {
      return x >= min && x <= max;
   }

   private boolean processFile(File f) {
      double minN = globalValidationConfig.getMinNodes();
      double maxN = globalValidationConfig.getMaxNodes();
      double minT = globalValidationConfig.getMinThreads();
      double maxT = globalValidationConfig.getMaxThreads();
      double minW = globalValidationConfig.getMinWrites();
      double maxW = globalValidationConfig.getMaxWrites();
      double minR = globalValidationConfig.getMinReads();
      double maxR = globalValidationConfig.getMaxReads();
      try {
         RadargunCsvParser parser = new RadargunCsvParser(f.getPath());
         boolean ok = in(parser.getNumNodes(), minN, maxN) &&
               in(parser.getAvgParam("AvgPutsPerWrTransaction"), minW, maxW)
               && in(parser.getAvgParam("NUM_THREADS"), minT, maxT)
               && in(parser.getAvgParam("NUM_KEYS"), minR, maxR);
         if (parser.isParam("NUM_WHAREHOUSES")) {
            minW = globalValidationConfig.getMinWarehouses();
            maxW = globalValidationConfig.getMaxWarehouses();
            ok &= in(parser.getAvgParam("NUM_WHAREHOUSES"), minW, maxW);
         }
         return ok;
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      } catch (NullPointerException n) {
         System.out.println("I had a nullPointerException in " + f.getAbsolutePath());
         return false;
      }
   }
}
