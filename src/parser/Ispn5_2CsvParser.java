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
      double writes = getSumParam("READ_COUNT");
      return writes / time;
   }

   public double readThroughput() {
      double time = getTestSecDuration();
      double reads = getSumParam("WRITE_COUNT");
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
   public double remoteGetRtt(){
      return getAvgParam("AvgRemoteGetRtt");
   }

   public double numReadsBeforeFirstWrite() {
      return getAvgParam("NumReadsBeforeWrite");
   }
}
