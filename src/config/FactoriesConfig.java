package config;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class FactoriesConfig {

   private String printerClass ;

   private String validatorConfig;

   public String getPrinterClass() {
      return printerClass;
   }

   public void setPrinterClass(String printerClass) {
      this.printerClass = printerClass;
   }

   public String getValidatorConfig() {
      return validatorConfig;
   }

   public void setValidatorConfig(String validatorConfig) {
      this.validatorConfig = validatorConfig;
   }
}
