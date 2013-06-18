package config;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt
 *         Date: 11/10/12
 */
public class GlobalValidationConfig {

   private int numThreads;
   private String testPath;
   private String output;

   private double minThreads;
   private double maxThreads;
   private double minNodes;
   private double maxNodes;
   private double minWrites;
   private double maxWrites;
   private double minReads;
   private double maxReads;

   private double minWarehouses;
   private double maxWarehouses;
   private double minOrderStatus;
   private double maxOrderStatus;
   private double minPayment;
   private double maxPayment;


   private double minReplicationDegree;
   private double maxReplicationDegree;

   public double getMinReplicationDegree() {
      return minReplicationDegree;
   }

   public void setMinReplicationDegree(double minReplicationDegree) {
      this.minReplicationDegree = minReplicationDegree;
   }

   public double getMaxReplicationDegree() {
      return maxReplicationDegree;
   }

   public void setMaxReplicationDegree(double maxReplicationDegree) {
      this.maxReplicationDegree = maxReplicationDegree;
   }

   public double getMinWarehouses() {
      return minWarehouses;
   }

   public void setMinWarehouses(double minWarehouses) {
      this.minWarehouses = minWarehouses;
   }

   public double getMaxWarehouses() {
      return maxWarehouses;
   }

   public void setMaxWarehouses(double maxWarehouses) {
      this.maxWarehouses = maxWarehouses;
   }

   public double getMinOrderStatus() {
      return minOrderStatus;
   }

   public void setMinOrderStatus(double minOrderStatus) {
      this.minOrderStatus = minOrderStatus;
   }

   public double getMaxOrderStatus() {
      return maxOrderStatus;
   }

   public void setMaxOrderStatus(double maxOrderStatus) {
      this.maxOrderStatus = maxOrderStatus;
   }

   public double getMinPayment() {
      return minPayment;
   }

   public void setMinPayment(double minPayment) {
      this.minPayment = minPayment;
   }

   public double getMaxPayment() {
      return maxPayment;
   }

   public void setMaxPayment(double maxPayment) {
      this.maxPayment = maxPayment;
   }

   public double getMinReads() {
      return minReads;
   }

   public void setMinReads(double minReads) {
      this.minReads = minReads;
   }

   public double getMaxReads() {
      return maxReads;
   }

   public void setMaxReads(double maxReads) {
      this.maxReads = maxReads;
   }

   public double getMinThreads() {
      return minThreads;
   }

   public void setMinThreads(double minThreads) {
      this.minThreads = minThreads;
   }

   public double getMaxThreads() {
      return maxThreads;
   }

   public void setMaxThreads(double maxThreads) {
      this.maxThreads = maxThreads;
   }

   public double getMinNodes() {
      return minNodes;
   }

   public void setMinNodes(double minNodes) {
      this.minNodes = minNodes;
   }

   public double getMaxNodes() {
      return maxNodes;
   }

   public void setMaxNodes(double maxNodes) {
      this.maxNodes = maxNodes;
   }

   public double getMinWrites() {
      return minWrites;
   }

   public void setMinWrites(double minWrites) {
      this.minWrites = minWrites;
   }

   public double getMaxWrites() {
      return maxWrites;
   }

   public void setMaxWrites(double maxWrites) {
      this.maxWrites = maxWrites;
   }

   public String getTestPath() {
      return testPath;
   }

   public void setTestPath(String testPath) {
      this.testPath = testPath;
   }

   public String getOutput() {
      return output;
   }

   public void setOutput(String output) {
      this.output = output;
   }

   public int getNumThreads() {
      return numThreads;
   }

   public void setNumThreads(int numThreads) {
      this.numThreads = numThreads;
   }

   @Override
   public String toString() {
      return "GlobalValidationConfig{" +
            "numThreads=" + numThreads +
            ", testPath='" + testPath + '\'' +
            ", output='" + output + '\'' +
            ", minThreads=" + minThreads +
            ", maxThreads=" + maxThreads +
            ", minNodes=" + minNodes +
            ", maxNodes=" + maxNodes +
            ", minWrites=" + minWrites +
            ", maxWrites=" + maxWrites +
            ", minReads=" + minReads +
            ", maxReads=" + maxReads +
            ", minWarehouses=" + minWarehouses +
            ", maxWarehouses=" + maxWarehouses +
            ", minOrderStatus=" + minOrderStatus +
            ", maxOrderStatus=" + maxOrderStatus +
            ", minPayment=" + minPayment +
            ", maxPayment=" + maxPayment +
            '}';
   }
}
