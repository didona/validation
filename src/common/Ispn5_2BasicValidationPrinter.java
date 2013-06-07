package common;

import parser.Ispn5_2CsvParser;
import printers.ValidationPrinter;

import java.util.List;


/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 04/10/12
 */
public abstract class Ispn5_2BasicValidationPrinter<V extends ValidatedScenario<Ispn5_2CsvParser>> extends ValidationPrinter<Ispn5_2CsvParser, V> {


   public Ispn5_2BasicValidationPrinter(String outpath, List<V> validatedScenarios) {
      super(outpath, validatedScenarios);
   }

   protected abstract void _header();

   protected abstract void _line(V validatedScenario);

   protected final void header() {
      put("ReplicationDegree");
      put("Numkeys");
      put("NumWrites");
      put("NumThread");
      put("NumNodes");
      put("Warehouses");
      put("Parallelism");
      put("CPU");
      put("Memory");
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
      put("LocalLockHoldTime");
      put("LocalSuccessfulLockHoldTime");
      put("LocalLocalAbortLockHoldTime");
      put("LocalRemoteAbortLockHoldTime");
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
      put("NumLocalCommitsWait");
      put("RemoteCommitWaitTime");
      put("NumRemoteCommitsWait");
      put("RemoteGetWaitTime");
      put("NumRemoteGetsWait");
      put("TotalResponseTimeWrXact");
      put("TotalResponseTimeROXact");
      put("LocalResponseTimeWrXact");
      put("LocalResponseTimeROXact");
      put("LocalServiceTimeWrXact");
      put("TotalServiceTimeWrXact");
      put("LocalServiceTimeROXact");
      put("RG_WrR");
      put("RG_ROR");
      put("RG_COMMIT");
      put("BusinessLogicWrXactS");
      put("BusinessLogicROXactS");
      put("BusinessLogicWrXactR");
      put("BusinessLogicROXactR");
      put("RemoteRemoteGetServiceTime");
      put("RemoteRemoteGetResponseTime");
      put("LocalRemoteGetServiceTime");
      put("LocalRemoteGetResponseTime");
      put("CommitCommandServiceTime");
      put("CommitCommandResponseTime");
      put("PrepareCommandServiceTime");
      put("PrepareCommandResponseTime");
      put("EarlyAbortProb");
      put("PrepareLocalAbortProb");
      put("PrepareRemoteAbortProb");
      _header();
   }

   protected final void line(V validatedScenario) {
      Ispn5_2CsvParser csvParser = validatedScenario.getRelevantCsv();
      put(csvParser.replicationDegree());
      put(csvParser.numKeys());
      put(csvParser.putsPerWrXact());
      double threads = csvParser.numThreads();
      put(threads);
      double nodes = csvParser.getNumNodes();
      put(nodes);
      put(csvParser.numWarehouses());
      put(nodes * threads);
      put(csvParser.cpu());
      put(csvParser.mem());
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
      put(csvParser.localSuccessfulHoldtime());
      put(csvParser.localLocalAbortHoldTime());
      put(csvParser.localRemoteAbortHoldTime());
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
      put(csvParser.numLocalCommitsWait());
      put(csvParser.remoteCommitWaitTime());
      put(csvParser.numRemoteCommitsWait());
      put(csvParser.remoteGetWaitTime());
      put(csvParser.numRemoteGetsWait());
      put(csvParser.totalResponseTimeWrXact());
      put(csvParser.totalResponseTimeROXact());
      put(csvParser.localResponseTimeWrXact());
      put(csvParser.localResponseTimeROXact());
      put(csvParser.localServiceTimeWrXact());
      put(csvParser.totalServiceTimeWrXact());
      put(csvParser.localServiceTimeROXact());
      put(csvParser.RGSuxWrXactR());
      put(csvParser.RGROXactR());
      put(csvParser.RGWrCommitR());
      put(csvParser.businessLogicWrXactS());
      put(csvParser.businessLogicROXactS());
      put(csvParser.businessLogicWrXactR());
      put(csvParser.businessLogicROXactR());
      put(csvParser.remoteRemoteGetServiceTime());
      put(csvParser.remoteRemoteGetResponseTime());
      put(csvParser.localRemoteGetServiceTime());
      put(csvParser.localRemoteGetResponseTime());
      put(csvParser.commitCommandServiceTime());
      put(csvParser.commitCommandResponseTime());
      put(csvParser.prepareCommandServiceTime());
      put(csvParser.prepareCommandResponseTime());
      put(csvParser.earlyAbortProbability());
      put(csvParser.prepareLocalAbortProbability());
      put(csvParser.prepareRemoteAbortProbability());
      _line(validatedScenario);
   }

}
