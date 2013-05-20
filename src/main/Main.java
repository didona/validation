package main;

import config.FactoriesConfig;
import config.GlobalValidationConfig;
import config.ValidationConfiguration;
import configuration.xml.DXmlParser;
import factories.PrinterFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import printers.ValidationPrinter;

import java.io.IOException;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class Main {

   private final static Log log = LogFactory.getLog(Main.class);

   public static void main(String[] args) throws Exception {
      ValidationConfiguration conf = (ValidationConfiguration) new DXmlParser().parse("conf/conf.xml");
      GlobalValidationConfig globalValidationConfig = conf.getGlobalValidationConfig();
      FactoriesConfig factoriesConfig = conf.getFactoriesConfig();
      //Util.init(8);
      String testPath = globalValidationConfig.getTestPath();
      log.trace("Test path is " + testPath);
      String outPath = globalValidationConfig.getOutput() + ".csv";
      GlobalValidator gv = new GlobalValidator(globalValidationConfig, factoriesConfig);
      gv.validateAll();
      log.trace("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n Validation Ended\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      ValidationPrinter vp = PrinterFactory.buildPrinter(factoriesConfig, gv.getValidatedScenarios(), outPath);
      vp.printValidation();
      System.out.println("Output file produced in "+outPath);
      System.exit(1);
   }

}
