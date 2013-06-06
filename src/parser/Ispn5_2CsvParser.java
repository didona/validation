package parser;


import parse.RadargunCsvParser;

import java.io.IOException;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class Ispn5_2CsvParser extends RadargunCsvParser {

   public Ispn5_2CsvParser(String path) throws IOException {
      super(path);
   }

   public double numWarehouses() {
      if (isParam("NUM_WAREHOUSES"))
         return getAvgParam("NUM_WAREHOUSES");
      return 0;
   }

   public double writeThroughput() {
      double time = getTestSecDuration();
      double writes = numWriteXact();
      return writes / time;
   }

   public double readThroughput() {
      double time = getTestSecDuration();
      double reads = numReadXact();
      return reads / time;
   }

   public double numThreads() {
      return getAvgParam("NUM_THREADS");
   }

   public double numKeys() {
      return getAvgParam("NUM_KEYS");
   }

   public double localReadProbability() {
      double remoteReads = getAvgParam("NumberOfRemoteGets");
      double totalReads = getAvgParam("NumberOfGets");
      return 1.0D - remoteReads / totalReads;
   }

   public double remoteNodesInCommit() {
      return getAvgParam("AvgNumAsyncSentCommitMsgs");
   }

   public double nodesInCommit() {
      return getAvgParam("AvgNumNodesCommit");
   }

   public double prepareRtt() {
      return getAvgParam("AvgPrepareRtt");
   }

   public double holdTime() {
      return getAvgParam("AvgLockHoldTime");
   }

   public double localHoldTime() {
      return getAvgParam("AvgLocalLockHoldTime");
   }

   public double localSuccessfulHoldtime() {
      return getAvgParam("AvgLocalSuccessfulLockHoldTime");
   }

   public double localLocalAbortHoldTime() {
      return getAvgParam("AvgLocalLocalAbortLockHoldTime");
   }

   public double localRemoteAbortHoldTime() {
      return getAvgParam("AvgLocalRemoteAbortLockHoldTime");
   }

   public double remoteHoldTime() {
      return getAvgParam("AvgRemoteLockHoldTime");
   }

   public double netAsyncCommit() {
      return getAvgParam("AvgCommitAsync");
   }

   public double netAsyncRollback() {
      return getAvgParam("AvgRollbackAsync");
   }

   public double replicationDegree() {
      return getAvgParam("ReplicationDegree");
   }

   public double cpu() {
      return getAvgParam("CPU_USAGE");
   }

   public double mem() {
      return getAvgParam("MEM_USAGE");
   }

   //TODO: take the stats also at ispn level
   public double earlyAbortProbability() {
      double localFailures = getAvgParam("LOCAL_FAILURES");
      double failed = getAvgParam("LOCAL_FAILURES") + getAvgParam("REMOTE_FAILURES");
      double ok = getAvgParam("WRITE_COUNT");
      return localFailures / (failed + ok);
   }

   public double commitProbability() {
      double failed = getAvgParam("LOCAL_FAILURES") + getAvgParam("REMOTE_FAILURES");
      double ok = getAvgParam("WRITE_COUNT") + getAvgParam("READ_COUNT");
      return ok / (ok + failed);
   }

   public double writeXactCommitProbability() {
      double failed = getAvgParam("LOCAL_FAILURES") + getAvgParam("REMOTE_FAILURES");
      double ok = getAvgParam("WRITE_COUNT");
      return ok / (ok + failed);
   }

   public double remoteReadsPerROXact() {
      return getAvgParam("AvgRemoteGetsPerROTransaction");
   }

   public double remoteReadsPerWrXact() {
      return getAvgParam("AvgRemoteGetsPerWrTransaction");
   }

   public double writePercentageXact() {
      return getAvgParam("PercentageWriteTransactions");
   }

   public double readsPerROXact() {
      return getAvgParam("AvgGetsPerROTransaction");
   }

   public double readsPerWrXact() {
      return getAvgParam("AvgGetsPerWrTransaction");
   }

   public double putsPerWrXact() {
      return getAvgParam("AvgPutsPerWrTransaction");
   }

   public double remoteGetRtt() {
      return getAvgParam("AvgRemoteGetRtt");
   }

   public double numReadsBeforeFirstWrite() {
      return getAvgParam("NumReadsBeforeWrite");
   }

   public double sizePrepareMsg() {
      return getAvgParam("AvgPrepareCommandSize");
   }

   public double sizeCommitMsg() {
      return getAvgParam("AvgCommitCommandSize");
   }

   public double sizeRollbackMsg() {
      return getAvgParam("AvgRollbackCommandSize");
   }

   public double sizeRemoteGetMsg() {
      return getAvgParam("AvgClusteredGetCommandSize");
   }

   public double sizeRemoteGetReplyMsg() {
      return getAvgParam("AvgClusteredGetCommandReplySize");
   }

   //TODO: switch to ISPN stats
   public double numRGAborts() {
      return getSumParam("LOCAL_FAILURES") + getSumParam("REMOTE_FAILURES");
   }

   public double RGSuxWrXactR() {
      return getAvgParam("SUX_UPDATE_XACT_RESPONSE");
   }

   public double RGROXactR() {
      return getAvgParam("SUX_READ_ONLY_XACT_RESPONSE");
   }

   public double RGWrCommitR() {
      return getAvgParam("COMMIT_TIME");
   }

   public double numAborts() {
      return getSumParam("NumAbortedXacts");
   }

   public double numWriteXact() {
      return getSumParam("WRITE_COUNT");
   }

   public double numReadXact() {
      return getSumParam("READ_COUNT");
   }

   public double numEarlyAborts() {
      return numAborts() - (numLocalPrepareAborts() + numRemotePrepareAborts());
   }

   public double numLocalPrepareAborts() {
      double prepareDead = numXactToPrepare() - numWriteXact();
      return prepareDead - numRemotePrepareAborts();
   }

   public double numRemotePrepareAborts() {
      return getSumParam("RemotelyDeadXact");
   }

   public double numXactToPrepare() {
      return getSumParam("UpdateXactToPrepare");
   }

   public double remoteCommitWaitTime() {
      return getAvgParam("WaitedTimeInRemoteCommitQueue");
   }

   public double localCommitWaitTime() {
      return getAvgParam("WaitedTimeInLocalCommitQueue");
   }

   public double remoteGetWaitTime() {
      return getAvgParam("GMUClusteredGetCommandWaitingTime");
   }

   public double totalResponseTimeWrXact() {
      return getAvgParam("LocalUpdateTxTotalResponseTime");
   }

   public double totalResponseTimeROXact() {
      return getAvgParam("ReadOnlyTxTotalResponseTime");
   }

   public double businessLogicWrXactR() {
      double remoteGetCost = localRemoteGetResponseTime();
      double numRemoteRd = remoteReadsPerWrXact();
      double local = localResponseTimeWrXact();
      return local - remoteGetCost * numRemoteRd;
   }

   public double businessLogicROXactR() {
      double remoteGetCost = localRemoteGetResponseTime();
      double numRemoteRd = remoteReadsPerROXact();
      double local = localResponseTimeROXact();
      return local - remoteGetCost * numRemoteRd;
   }

   public double businessLogicWrXactS() {
      double remoteGetCost = localRemoteGetServiceTime();
      System.out.println("RemoteGetS "+remoteGetCost);
      double numRemoteRd = remoteReadsPerWrXact();
      System.out.println("RemoteReads "+numRemoteRd);
      double local = localServiceTimeWrXact();
      System.out.println("LocalS "+local);
      return local - remoteGetCost * numRemoteRd;
   }

   public double businessLogicROXactS() {
      double remoteGetCost = localRemoteGetServiceTime();
      double numRemoteRd = remoteReadsPerROXact();
      double local = localServiceTimeROXact();
      return local - remoteGetCost * numRemoteRd;
   }


   public double localRemoteGetResponseTime() {
      return getAvgParam("RemoteGetResponseTime");
   }


   public double localRemoteGetServiceTime() {
      return getAvgParam("RemoteGetServiceTime");
   }


   public double localResponseTimeWrXact() {
      return getAvgParam("LocalUpdateTxLocalResponseTime");
   }

   public double localResponseTimeROXact() {
      return getAvgParam("LocalReadOnlyTxLocalResponseTime");
   }

   public double localServiceTimeWrXact() {
      return getAvgParam("LocalUpdateTxTotalCpuTime");
   }

   public double localServiceTimeROXact() {
      return getAvgParam("LocalReadOnlyTxLocalServiceTime");
   }

   public double remoteRemoteGetServiceTime() {
      return getAvgParam("GMUClusteredGetCommandServiceTime");
   }

   public double remoteRemoteGetResponseTime() {
      return getAvgParam("GMUClusteredGetCommandResponseTime");
   }

   public double prepareCommandServiceTime() {
      return getAvgParam("LocalUpdateTxPrepareServiceTime");
   }

   public double prepareCommandResponseTime() {
      return getAvgParam("LocalUpdateTxPrepareResponseTime");
   }

   public double commitCommandServiceTime() {
      return getAvgParam("LocalUpdateTxCommitServiceTime");
   }

   public double commitCommandResponseTime() {
      return getAvgParam("LocalUpdateTxCommitResponseTime");
   }


   public double numLocalCommitsWait() {
      return getAvgParam("NumWaitedLocalCommits");
   }

   public double numRemoteCommitsWait() {
      return getAvgParam("NumWaitedRemoteCommits");
   }

   public double numRemoteGetsWait() {
      return getAvgParam("NumWaitedRemoteGets");
   }


   public double remoteCommitCommandServiceTime() {
      return getAvgParam("RemoteUpdateTxCommitServiceTime");
   }

   public double remoteCommitCommandResponseTime() {
      return getAvgParam("RemoteUpdateTxCommitResponseTime");
   }


   public double localLocalRollbackServiceTime(){
      return getAvgParam("LocalUpdateTxLocalRollbackServiceTime");
   }

   public double localLocalRollbackResponseTime(){
      return getAvgParam("LocalUpdateTxLocalRollbackResponseTime");
   }
   public double localRemoteRollbackServiceTime(){
      return getAvgParam("LocalUpdateTxRemoteRollbackServiceTime");
   }

   public double localRemoteRollbackResponseTime(){
      return getAvgParam("LocalUpdateTxRemoteRollbackResponseTime");
   }
   public double remoteRollbackServiceTime(){
      return getAvgParam("RemoteUpdateTxRollbackServiceTime");
   }

   public double remoteRollbackResponseTime(){
      return getAvgParam("RemoteUpdateTxRollbackResponseTime");
   }

   public double remotePrepareServiceTime(){
      return getAvgParam("RemoteUpdateTxPrepareServiceTime");
   }

   public double remotePrepareResponseTime(){
      return getAvgParam("RemoteUpdateTxPrepareResponseTime");
   }


}
