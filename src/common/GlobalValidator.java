package common;

import config.FactoriesConfig;
import config.GlobalValidationConfig;
import parse.RadargunCsvParser;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */


public class GlobalValidator {

   protected String testsPath;
   private int THREADS = 1;

   private FactoriesConfig factoriesConfig;
   private GlobalValidationConfig globalValidationConfig;

   List<ValidatedScenario<RadargunCsvParser>> validatedScenarios = new LinkedList<ValidatedScenario<RadargunCsvParser>>();


   public GlobalValidator(GlobalValidationConfig conf, FactoriesConfig factoriesConfig) {
      this.testsPath = conf.getTestPath();
      this.THREADS = this.numThreads(conf, factoriesConfig);
      this.factoriesConfig = factoriesConfig;
      this.globalValidationConfig = conf;
   }

   private int numThreads(GlobalValidationConfig conf, FactoriesConfig fac) {
      return conf.getNumThreads();
   }

   public void validateAll() {
      //Populate the job task
      List<File> folderList = this.listTestFolders(this.testsPath);

      Thread[] threads = new ValidatorThread[THREADS];
      for (int i = 0; i < THREADS; i++) {
         threads[i] = new ValidatorThread(folderList, validatedScenarios, factoriesConfig, globalValidationConfig);
         threads[i].start();
      }

      for (int i = 0; i < THREADS; i++) {
         try {
            threads[i].join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }


   private List<File> listTestFolders(String testPath) {
      LinkedList<File> ret = new LinkedList<File>();
      File[] dir = new File(testPath).listFiles();
      //noinspection ConstantConditions
      assert dir != null;
      for (File f : dir) {
         if (f.isDirectory()) {
            ret.add(f);
         }
      }
      return ret;
   }

   public static boolean csv(File f) {
      return f.getAbsolutePath().endsWith(".csv");
   }

   public List<ValidatedScenario<RadargunCsvParser>> getValidatedScenarios() {
      return this.validatedScenarios;
   }

}
