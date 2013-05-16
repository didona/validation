package config;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class ValidationConfiguration {

   private GlobalValidationConfig globalValidationConfig;
   private FactoriesConfig factoriesConfig;

   public GlobalValidationConfig getGlobalValidationConfig() {
      return globalValidationConfig;
   }

   public void setGlobalValidationConfig(GlobalValidationConfig globalValidationConfig) {
      this.globalValidationConfig = globalValidationConfig;
   }

   public FactoriesConfig getFactoriesConfig() {
      return factoriesConfig;
   }

   public void setFactoriesConfig(FactoriesConfig factoriesConfig) {
      this.factoriesConfig = factoriesConfig;
   }
}
