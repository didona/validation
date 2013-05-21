package factories;

import config.FactoriesConfig;
import common.ValidatedScenario;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class PrinterFactory {

   public static <T> T buildPrinter(FactoriesConfig factoriesConfig, List<ValidatedScenario> validatedScenarios, String outPath) throws Exception {
      Class<T> clazz = (Class<T>) Class.forName(factoriesConfig.getPrinterClass());
      Class param2 = validatedScenarios.getClass();
      Class param1 = outPath.getClass();
      Constructor<T> builder = clazz.getDeclaredConstructor(param1, param2);
      return builder.newInstance(outPath, validatedScenarios);
   }
}
