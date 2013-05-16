package factories;


import common.Validator;
import config.FactoriesConfig;
import configuration.xml.DXmlParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class ValidatorFactory {

   public static Validator buildValidator(FactoriesConfig config){
      String configFile = config.getValidatorConfig();
      return (Validator)new DXmlParser().parse(configFile);
   }

}
