package gmu;

import common.ValidatedScenario;
import ispn_53.output.ISPN_53_D_TPC_GMU_Result;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuValidatedScenario extends ValidatedScenario<Ispn5_2CsvParser> {

   private ISPN_53_D_TPC_GMU_Result result;

   public GmuValidatedScenario(Ispn5_2CsvParser relevantCsv, ISPN_53_D_TPC_GMU_Result result) {
      super(relevantCsv);
      this.result = result;
   }


   public double predThroughput() {
      return result.throughput();
   }

   public double predThroughputR() {
      return result.roThroughput();
   }

   public double predThroughputW() {
      return result.wrThroughput();
   }

   public double wrR() {
      return result.updateXactR();
   }

   public double roR() {
      return result.readOnlyXactR();
   }

   public double updateLocalR() {
      return result.updateXactLocalR();
   }

   public double updatePrepareR() {
      return result.updateXactPrepareR();
   }

   public double updateCommitR() {
      return result.updateXactCommitR();
   }

   public double remoteGetR() {
      return result.remoteGetR();
   }

   public double cpuUtilization() {
      return result.cpuUtilization();
   }

   public double earlyAbortP() {
      return result.getProbabilities().encounterTimeEarlyAbortProbability();
   }

   public double localAbortP() {
      return result.getProbabilities().localPrepareAbortProbability();
   }

   public double remoteAbortP() {
      return result.getProbabilities().remotePrepareAbortProbability();
   }

   public double avgR() {
      return result.avgR();
   }

   public double localUpdateU() {
      double totalUpS = getRelevantCsv().totalServiceTimeWrXact();
      double okLambdaW = getRelevantCsv().writeThroughput() * 1e-6 / getRelevantCsv().getNumNodes();
      return totalUpS * okLambdaW;
   }

   public double localRoU() {
      double totalRoS = getRelevantCsv().localServiceTimeROXact();
      double okLambdaR = getRelevantCsv().readThroughput() * 1e-6 / getRelevantCsv().getNumNodes();
      return totalRoS * okLambdaR;
   }

   public double localEarlyAbortU() {
      double earlyAbortS = getRelevantCsv().localServiceTimeWrXact() * .5;
      double earlyAbortLambda = getRelevantCsv().numEarlyAborts() / (getRelevantCsv().getTestSecDuration() * getRelevantCsv().getNumNodes() * 1e6);
      return earlyAbortS * earlyAbortLambda;
   }

   public double localLocalAbortU() {
      double localAbortS = getRelevantCsv().localServiceTimeWrXact() + getRelevantCsv().localLocalRollbackServiceTime();
      double localAbortLambda = (+getRelevantCsv().numLocalPrepareAborts()) / (getRelevantCsv().getTestSecDuration() * getRelevantCsv().getNumNodes() * 1e6);
      return localAbortS * localAbortLambda;
   }

   public double localRemoteAbortU() {
      double remoteAbortS = getRelevantCsv().localServiceTimeWrXact() + getRelevantCsv().localRemoteRollbackServiceTime();
      double remoteAbortLambda = getRelevantCsv().numRemotePrepareAborts() / (getRelevantCsv().getTestSecDuration() * getRelevantCsv().getNumNodes() * 1e6);

      return remoteAbortS * remoteAbortLambda;
   }

   public double remoteOkU() {
      double remoteRemoteS = getRelevantCsv().remotePrepareServiceTime() + getRelevantCsv().remoteCommitCommandServiceTime();
      double remoteOkLambda = (getRelevantCsv().writeThroughput() * 1e-6 * ((getRelevantCsv().getNumNodes() - 1) / getRelevantCsv().getNumNodes())) * getRelevantCsv().remoteNodesInCommit() / getRelevantCsv().getNumNodes(); //Prob. of being involved in a remote prepare
      return remoteRemoteS * remoteOkLambda;
   }

   public double remoteGetU() {
      double remoteGetS = getRelevantCsv().remoteRemoteGetServiceTime();
      double remoteGetLambda = (1e-6 / getRelevantCsv().getTestSecDuration()) * getRelevantCsv().numRemoteGets() * (getRelevantCsv().getNumNodes() - 1) / getRelevantCsv().getNumNodes();
      return remoteGetLambda * remoteGetS;
   }


}
