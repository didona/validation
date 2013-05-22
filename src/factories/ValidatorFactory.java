package factories;


import common.Validator;
import config.FactoriesConfig;
import configuration.xml.DXmlParser;
import parse.RadargunCsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class ValidatorFactory {

   public static Validator<RadargunCsvParser> buildValidator(FactoriesConfig config){
      String configFile = config.getValidatorConfig();
      return (Validator<RadargunCsvParser>)new DXmlParser().parse(configFile);
   }

}
