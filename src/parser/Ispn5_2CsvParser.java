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

   public double remoteHoldTime() {
      return getAvgParam("AvgRemoteLockHoldTime");
   }

   public double netAsyncCommit() {
      return getAvgParam("AvgCommitAsync");
   }

   public double netAsyncRollback() {
      return getAvgParam("AvgRollbackAsync");
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

   public double businessLogicWrXact() {
      double remoteGetCost = remoteGetResponseTime();
      double numRemoteRd = remoteReadsPerWrXact();
      double local = localResponseTimeWrXact();
      return local - remoteGetCost * numRemoteRd;
   }

   public double businessLogicROXact() {
      double remoteGetCost = remoteGetResponseTime();
      double numRemoteRd = remoteReadsPerROXact();
      double local = localResponseTimeROXact();
      return local - remoteGetCost * numRemoteRd;
   }

   public double remoteGetResponseTime() {
      return getAvgParam("RemoteGetResponseTime");
   }


   public double remoteGetServiceTime() {
      return getAvgParam("RemoteGetServiceTime");
   }


   public double localResponseTimeWrXact() {
      return getAvgParam("LocalUpdateTxTotalResponseTime");
   }

   public double localResponseTimeROXact() {
      return getAvgParam("LocalReadOnlyTxLocalResponseTime");
   }

   public double localServiceTimeWrXact() {
      return getAvgParam("LocalUpdateTxTotalServiceTime");
   }

   public double localServiceTimeROXact() {
      return getAvgParam("LocalReadOnlyTxLocalServiceTime");
   }


}
