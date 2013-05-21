package common;

import parser.Ispn5_2CsvParser;
import printers.ValidationPrinter;

import java.util.List;


/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 04/10/12
 */
public abstract class Ispn5_2BasicValidationPrinter extends ValidationPrinter<Ispn5_2CsvParser> {


   public Ispn5_2BasicValidationPrinter(String outpath, List<ValidatedScenario> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   protected final void basicHeader() {
      put("Numkeys");
      put("NumWrites");
      put("NumThread");
      put("NumNodes");
      put("Warehouses");
      put("Parallelism");
      put("Throughput");
      put("WriteThroughput");
      put("ReadThroughput");
      put("EarlyAborts");
      put("LocalPrepareAborts");
      put("RemotePrepareAborts");
      put("WriteXactPercentage");
      put("WriteCommitProbability");
      put("TotalCommitProbability");
      put("Locality");
      put("NumReadsPerROXact");
      put("NumReadsPerWrXact");
      put("NumPutsPerWrXact");
      put("NodesInCommit");
      put("RemoteNodesInCommit");
      put("RemoteReadsInROXact");
      put("RemoteReadsInWrXact");
      put("LockHoldTime");
      put("LocalLocHoldTime");
      put("RemoteLockHoldTime");
      put("PrepareRtt");
      put("CommitAsyncNet");
      put("RollbackAsyncNet");
      put("RemoteGetRtt");
      put("NumReadsAfterFirstWrite");
      put("PrepareMessageSize");
      put("CommitMessageSize");
      put("RollbackMessageSize");
      put("RemoteGetMessageSize");
      put("RemoteGetReplyMessageSize");
      put("LocalCommitWaitTime");
      put("RemoteCommitWaitTime");
      put("RemoteGetWaitTime");
      put("TotalResponseTimeWrXact");
      put("TotalResponseTimeROXact");
      put("LocalResponseTimeWrXact");
      put("LocalResponseTimeROXact");
      put("LocalServiceTimeWrXact");
      put("LocalServiceTimeROXact");
      put("BusinessLogicWrXact");
      put("BusinessLogicROXact");
   }

   protected final void printBasicStats(ValidatedScenario<Ispn5_2CsvParser> validatedScenario) {
      Ispn5_2CsvParser csvParser = validatedScenario.getRelevantCsv();
      double keys = csvParser.numKeys();
      put(keys);
      double writes = csvParser.putsPerWrXact();
      put(writes);
      double threads = csvParser.numThreads();
      put(threads);
      double nodes = csvParser.getNumNodes();
      put(nodes);
      put(csvParser.numWarehouses());
      put(nodes * threads);
      double writeT = csvParser.writeThroughput();
      double readT = csvParser.readThroughput();
      put(writeT + readT);
      put(writeT);
      put(readT);
      put(csvParser.numEarlyAborts());
      put(csvParser.numLocalPrepareAborts());
      put(csvParser.numRemotePrepareAborts());
      put(csvParser.writePercentageXact());
      put(csvParser.writeXactCommitProbability());
      put(csvParser.commitProbability());
      put(csvParser.localReadProbability());
      put(csvParser.readsPerROXact());
      put(csvParser.readsPerWrXact());
      put(csvParser.putsPerWrXact());
      put(csvParser.nodesInCommit());
      put(csvParser.remoteNodesInCommit());
      put(csvParser.remoteReadsPerROXact());
      put(csvParser.remoteReadsPerWrXact());
      put(csvParser.holdTime());
      put(csvParser.localHoldTime());
      put(csvParser.remoteHoldTime());
      put(csvParser.prepareRtt());
      put(csvParser.netAsyncCommit());
      put(csvParser.netAsyncRollback());
      put(csvParser.remoteGetRtt());
      put(csvParser.numReadsBeforeFirstWrite());
      put(csvParser.sizePrepareMsg());
      put(csvParser.sizeCommitMsg());
      put(csvParser.sizeRollbackMsg());
      put(csvParser.sizeRemoteGetMsg());
      put(csvParser.sizeRemoteGetReplyMsg());
      put(csvParser.localCommitWaitTime());
      put(csvParser.remoteCommitWaitTime());
      put(csvParser.remoteGetWaitTime());
      put(csvParser.totalResponseTimeWrXact());
      put(csvParser.totalResponseTimeROXact());
      put(csvParser.businessLogicWrXact());
      put(csvParser.businessLogicROXact());
   }

}
