package common;

import parse.RadargunCsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class ValidatedScenario<T extends RadargunCsvParser> {

   private T relevantCsv;

   public T getRelevantCsv() {
      return relevantCsv;
   }

   public void setRelevantCsv(T relevantCsv) {
      this.relevantCsv = relevantCsv;
   }

   public ValidatedScenario(T relevantCsv) {
      this.relevantCsv = relevantCsv;
   }
}
