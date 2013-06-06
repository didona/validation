package gmu;

import common.AbstractValidator;
import common.NotValidatedException;
import common.exceptions.TasException;
import ispn_53.QueueCpuFixedNetGmuTas;
import ispn_53.input.ISPN_52_TPC_GMU_Workload;
import ispn_53.input.physical.GmuCpuServiceTimes;
import ispn_53.input.physical.GmuFixedNetServiceTimes;
import ispn_53.input.physical.GmuQueueCpuFixedNetServiceTimes;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuFixedBocceValidator extends AbstractValidator<Ispn5_2CsvParser> {

   @Override
   public void validate(Ispn5_2CsvParser parser) throws NotValidatedException {
      ISPN_52_TPC_GMU_Workload workload = buildWorkload(parser);
      GmuQueueCpuFixedNetServiceTimes serviceTimes = buildServiceTimes(parser);
      QueueCpuFixedNetGmuTas tas = new QueueCpuFixedNetGmuTas();
      try {
         tas.solve(workload,serviceTimes);
      } catch (TasException e) {
         throw new NotValidatedException(e.getMessage());
      }

   }


   private ISPN_52_TPC_GMU_Workload buildWorkload(Ispn5_2CsvParser parser) {
      ISPN_52_TPC_GMU_Workload workload = new ISPN_52_TPC_GMU_Workload();
      //Common
      double numNodes = parser.getNumNodes();
      double writePercentage = parser.writePercentageXact();
      double wrPerXact = parser.numWriteXact();
      double threadsPerNode = parser.numThreads();
      double applicationContentionFactor = applicationContentionFactor(parser);
      double prepareMessageSize = parser.sizePrepareMsg();
      double mem = parser.mem();
      double numCores = 8;
      //Gmu-specific
      int firstWrite = 1;//(int) parser.numReadsBeforeFirstWrite();
      double localAccessProbability = parser.localReadProbability();
      double replicationDegree = parser.replicationDegree();
      double localPrimaryOwnerProbability = replicationDegree / numNodes;
      double readsPerROXact = parser.readsPerROXact();
      double readsPerWrXact = parser.readsPerWrXact();

      workload.setNumNodes(numNodes);
      workload.setWritePercentage(writePercentage);
      workload.setWriteOpsPerTx(wrPerXact);
      workload.setThreadsPerNode(threadsPerNode);
      workload.setApplicationContentionFactor(applicationContentionFactor);
      workload.setPrepareMessageSize(prepareMessageSize);
      workload.setMem(mem);
      workload.setNumCores(numCores);

      workload.setFirstWriteIndex(firstWrite);
      workload.setLocalAccessProbability(localAccessProbability);
      workload.setReplicationDegree(replicationDegree);
      workload.setLocalAccessProbability(localPrimaryOwnerProbability);
      workload.setReadsPerROXact(readsPerROXact);
      workload.setReadsPerWrXact(readsPerWrXact);

      return workload;
   }

   private GmuQueueCpuFixedNetServiceTimes buildServiceTimes(Ispn5_2CsvParser parser) {
      GmuCpuServiceTimes cpuS = buildCpuS(parser);
      GmuFixedNetServiceTimes netS = buildNetS(parser);
      return new GmuQueueCpuFixedNetServiceTimes(cpuS, netS);
   }

   private GmuCpuServiceTimes buildCpuS(Ispn5_2CsvParser parser) {
      double updateTxBusinessLogicS = parser.businessLogicWrXactS();
      double updateTxPrepareS = parser.prepareCommandServiceTime();
      double updateTxCommitS = parser.commitCommandServiceTime();
      double updateTxLocalLocalRollbackS = parser.localLocalRollbackServiceTime();
      double updateTxLocalRemoteRollbackS = parser.localRemoteRollbackServiceTime();

      double localRemoteGetS = parser.localRemoteGetServiceTime();
      double localGetS = 0;
      double remoteRemoteGetS = parser.remoteRemoteGetServiceTime();
      double putS = 0;

      double updateTxRemoteExecutionS = parser.remotePrepareServiceTime();
      double updateTxRemoteCommitS = parser.remoteCommitCommandServiceTime();
      double updateTxRemoteRollbackS = parser.remoteRollbackServiceTime();

      double readOnlyTxBusinessLogicS = parser.businessLogicROXactS();
      double readOnlyTxPrepareS = 0;
      double readOnlyTxCommitS = 0;
      GmuCpuServiceTimes cpu = new GmuCpuServiceTimes();

      cpu.setUpdateTxBusinessLogicS(updateTxBusinessLogicS);
      cpu.setUpdateTxPrepareS(updateTxPrepareS);
      cpu.setUpdateTxCommitS(updateTxCommitS);
      cpu.setUpdateTxLocalLocalRollbackS(updateTxLocalLocalRollbackS);
      cpu.setUpdateTxLocalRemoteRollbackS(updateTxLocalRemoteRollbackS);
      cpu.setLocalRemoteGetS(localRemoteGetS);
      cpu.setLocalGetS(localGetS);
      cpu.setRemoteRemoteGetS(remoteRemoteGetS);
      cpu.setPutS(putS);
      cpu.setUpdateTxRemoteExecutionS(updateTxRemoteExecutionS);
      cpu.setUpdateTxRemoteCommitS(updateTxRemoteCommitS);
      cpu.setUpdateTxRemoteRollbackS(updateTxRemoteRollbackS);
      cpu.setReadOnlyTxBusinessLogicS(readOnlyTxBusinessLogicS);
      cpu.setReadOnlyTxPrepareS(readOnlyTxPrepareS);
      cpu.setReadOnlyTxCommitS(readOnlyTxCommitS);
      System.out.println(cpu);
      return cpu;
   }

   private GmuFixedNetServiceTimes buildNetS(Ispn5_2CsvParser parser) {
      GmuFixedNetServiceTimes net = new GmuFixedNetServiceTimes();
      double prepareRtt = parser.prepareRtt();
      double commitNet = parser.netAsyncCommit();
      double remoteGetNet = parser.remoteGetRtt();
      double rollbackNet = parser.netAsyncRollback();
      net.setCommitNet(commitNet);
      net.setPrepareRtt(prepareRtt);
      net.setRemoteGetNet(remoteGetNet);
      net.setRollbackNet(rollbackNet);
      return net;
   }


   private double applicationContentionFactor(Ispn5_2CsvParser parser) {
      return 1.0D / parser.numKeys();
   }
}
