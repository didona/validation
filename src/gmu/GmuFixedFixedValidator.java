package gmu;/*
 * INESC-ID, Instituto de Engenharia de Sistemas e Computadores Investigação e Desevolvimento em Lisboa
 * Copyright 2013 INESC-ID and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

import ispn_53.input.physical.GmuCpuServiceTimesImpl;
import parser.Ispn5_2CsvParser;

/**
 * @author Diego Didona, didona@gsd.inesc-id.pt Date: 09/06/13
 */
public class GmuFixedFixedValidator extends GmuQueueFixedValidator {

   protected GmuCpuServiceTimesImpl buildCpuS(Ispn5_2CsvParser parser) {
      double updateTxBusinessLogicS = parser.businessLogicWrXactR();
      if (updateTxBusinessLogicS < 0) {
         System.out.println("UpdateTxBusinessLogic<0! Setting 1");
         updateTxBusinessLogicS = 1;
      }
      double updateTxPrepareS = parser.prepareCommandResponseTime();
      double updateTxCommitS = parser.commitCommandResponseTime();
      double updateTxLocalLocalRollbackS = parser.localLocalRollbackResponseTime();
      double updateTxLocalRemoteRollbackS = parser.localRemoteRollbackResponseTime();

      double localRemoteGetS = parser.localRemoteGetResponseTime();
      double localGetS = 0;
      double remoteRemoteGetS = parser.remoteRemoteGetResponseTime();
      double putS = 0;

      double updateTxRemoteExecutionS = parser.remotePrepareResponseTime();
      double updateTxRemoteCommitS = parser.remoteCommitCommandResponseTime();
      double updateTxRemoteRollbackS = parser.remoteRollbackResponseTime();

      double readOnlyTxBusinessLogicS = parser.localResponseTimeROXact();
      if (readOnlyTxBusinessLogicS < 0) {
         System.out.println("ReadOnlyTxBusinessLogic<0! Setting 1");
         readOnlyTxBusinessLogicS = 1;
      }
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
      System.out.println(cpu);
      return cpu;
   }


}
