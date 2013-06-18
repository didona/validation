package gmu;

import common.AbstractValidator;
import common.NotValidatedException;
import common.exceptions.TasException;
import gmu.GmuValidatedScenario;
import ispn_53.QueueCpuFixedNetGmuTas;
import ispn_53.input.ISPN_52_TPC_GMU_Workload;
import ispn_53.input.physical.GmuCpuServiceTimesImpl;
import ispn_53.input.physical.GmuFixedNetServiceTimes;
import ispn_53.input.physical.GmuQueueCpuFixedNetServiceTimes;
import ispn_53.output.ISPN_53_D_TPC_GMU_Result;
import parser.Ispn5_2CsvParser;

/**
 * // TODO: Document this
 *
 * @author diego
 * @since 4.0
 */
public class GmuQueueFixedValidator extends AbstractValidator<Ispn5_2CsvParser> {

   protected String tasConfig = null;
   private boolean open = true;

   public void setTasConfig(String tasConfig) {
      this.tasConfig = tasConfig;
   }

   @Override
   public final void validate(Ispn5_2CsvParser parser) throws NotValidatedException {
      ISPN_52_TPC_GMU_Workload workload = buildWorkload(parser);
      GmuQueueCpuFixedNetServiceTimes serviceTimes = buildServiceTimes(parser);
      QueueCpuFixedNetGmuTas tas;
      if(open)
         this.setTasConfig("conf/gmu/open/openFixed.xml");
      if(tasConfig==null)
       tas = new QueueCpuFixedNetGmuTas();
      else
         tas = new QueueCpuFixedNetGmuTas(tasConfig);
      ISPN_53_D_TPC_GMU_Result result;
      try {
         result = tas.solve(workload, serviceTimes);
      } catch (TasException e) {
         throw new NotValidatedException(e.getMessage()+"   "+e.getCause());
      }

      log.trace("I have a stable result");
      addValidatedScenario(new GmuValidatedScenario(parser, result));
   }


   private ISPN_52_TPC_GMU_Workload buildWorkload(Ispn5_2CsvParser parser) {
      ISPN_52_TPC_GMU_Workload workload = new ISPN_52_TPC_GMU_Workload();
      //Common
      double numNodes = parser.getNumNodes();
      double writePercentage = parser.writePercentageXact();
      double wrPerXact = parser.putsPerWrXact();
      double threadsPerNode = parser.numThreads();
      double applicationContentionFactor = applicationContentionFactor(parser);
      double prepareMessageSize = parser.sizePrepareMsg();
      double mem = parser.mem();
      double numCores = 1;
      double lambda = 0;
      if(tasConfig!=null)
          lambda = (parser.readThroughput()+parser.writeThroughput())*1e-6;

      //Gmu-specific
      int firstWrite = 1;//(int) parser.numReadsBeforeFirstWrite();
      double localAccessProbability = parser.localReadProbability();
      double replicationDegree = parser.replicationDegree();
      double readsPerROXact = parser.readsPerROXact();
      double readsPerWrXact = parser.readsPerWrXact();
      double primaryOwnerProb = 1.0D / numNodes;
      double localCommitW = parser.localCommitWaitTime();
      double remoteCommitW = parser.remoteCommitWaitTime();

      workload.setNumNodes(numNodes);
      workload.setWritePercentage(writePercentage);
      workload.setWriteOpsPerTx(wrPerXact);
      workload.setThreadsPerNode(threadsPerNode);
      workload.setApplicationContentionFactor(applicationContentionFactor);
      workload.setPrepareMessageSize(prepareMessageSize);
      workload.setMem(mem);
      workload.setNumCores(numCores);
      workload.setLambda(lambda);

      workload.setFirstWriteIndex(firstWrite);
      workload.setLocalAccessProbability(localAccessProbability);
      workload.setReplicationDegree(replicationDegree);
      workload.setReadsPerROXact(readsPerROXact);
      workload.setReadsPerWrXact(readsPerWrXact);
      workload.setLocalPrimaryOwnerProbability(primaryOwnerProb);
      workload.setLocalCommitQueueWaitingTime(localCommitW);
      workload.setRemoteCommitQueueWaitingTime(remoteCommitW);
      System.out.println(workload);
      return workload;
   }

   private GmuQueueCpuFixedNetServiceTimes buildServiceTimes(Ispn5_2CsvParser parser) {
      GmuCpuServiceTimesImpl cpuS = buildCpuS(parser);
      GmuFixedNetServiceTimes netS = buildNetS(parser);
      return new GmuQueueCpuFixedNetServiceTimes(cpuS, netS);
   }

   protected GmuCpuServiceTimesImpl buildCpuS(Ispn5_2CsvParser parser) {
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
      GmuCpuServiceTimesImpl cpu = new GmuCpuServiceTimesImpl();

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
      log.trace(cpu);
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
      System.out.println(net);
      return net;
   }


   private double applicationContentionFactor(Ispn5_2CsvParser parser) {
      return 1.0D / parser.numKeys();
   }
}
