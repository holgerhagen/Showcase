package org.educama.shipment.casemodel;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests the CMMN model.
 */
public class ShipmentCaseModelTest {

    private static final String CASE_KEY = "ShipmentCase";

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Test
    @Deployment(resources = "cmmn/ShipmentCase.cmmn")
    public void testCaseInitializationWithIncompleteData() {

        CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceByKey(CASE_KEY);

        showCaseOverview(caseInstance);

        // Case Instance active?
        assertTrue(caseInstance.isActive());

        // Milestone 'Shipment order completed' not reached?
        assertTrue(rule.getCaseService().createCaseExecutionQuery()
                .activityId("PlanItem_Milestone_ShipmentOrderCompleted").singleResult().isAvailable());

        // Task 'Complete shipment order' available?
        assertTrue(rule.getCaseService().createCaseExecutionQuery()
                .activityId("PlanItem_HumanTask_CompleteShipmentOrder").singleResult().isEnabled());

        // Stage 'Process shipment order' is not enabled?
        assertFalse(rule.getCaseService().createCaseExecutionQuery().activityId("PlanItem_Stage_ProcessShipmentOrder")
                .singleResult().isEnabled());

    }

    @Test
    @Deployment(resources = "cmmn/ShipmentCase.cmmn")
    public void testCompleteIncompleteCaseAfterCreation() {
        CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceByKey(CASE_KEY);
        CaseExecution caseExecution = processEngine().getCaseService().createCaseExecutionQuery().activityId("shipmentCasePlanModel")
                .singleResult();
        showCaseOverview(caseInstance);

        // Variable set to true to complete milestone
        processEngine().getCaseService().setVariable(caseExecution.getId(), "shipmentOrderComplete", new Boolean(true));
        showCaseOverview(caseInstance);

         // Milestone reached and stage activated?
         assertTrue(processEngine().getCaseService().createCaseExecutionQuery().activityId("PlanItem_Milestone_ShipmentOrderCompleted").singleResult()
         == null);
         assertTrue(processEngine().getCaseService().createCaseExecutionQuery().activityId("PlanItem_Stage_ProcessShipmentOrder").singleResult().isActive());

         // Task 'Complete shipment order' available?
         assertTrue(processEngine().getCaseService().createCaseExecutionQuery().activityId("Task_CompleteShipmentOrder").singleResult().isEnabled());

         // Stage 'Process shipment order' is not enabled?
         assertFalse(processEngine().getCaseService().createCaseExecutionQuery().activityId("Stage_ProcessShipmentOrder").singleResult().isEnabled());
    }

    /**
     * Helper method because it is not provided by
     * {@link org.camunda.bpm.engine.test.assertions.ProcessEngineTests}.
     *
     * @return current CaseService
     */
    private CaseService caseService() {
        return processEngine().getCaseService();
    }

    // method to display an overview of executions
    private void showCaseOverview(CaseInstance caseInstance) {
        List<CaseExecution> caseExecutionList = caseService().createCaseExecutionQuery()
                .caseInstanceId(caseInstance.getId()).list();
        System.out.println("------ Current List of Case Executions ------");

        caseExecutionList.stream().filter(caseExecution -> caseExecution.getId() == caseInstance.getId())
                .forEach(caseExecution -> System.out.println("Case Instance : " + caseExecution.getActivityName() + " ["
                        + caseExecution.getActivityType() + "]" + " - CaseExecutionId: " + caseExecution.getId()));

        caseExecutionList.stream().filter(caseExecution -> caseExecution.isActive())
                .forEach(caseExecution -> System.out.println("Running ('active'): " + caseExecution.getActivityName()
                        + " [" + caseExecution.getActivityType() + "]" + " - CaseExecutionId: "
                        + caseExecution.getId()));

        caseExecutionList.stream().filter(caseExecution -> caseExecution.isEnabled())
                .forEach(caseExecution -> System.out.println("Possible to start ('enabled'): "
                        + caseExecution.getActivityName() + " [" + caseExecution.getActivityType() + "]"
                        + " - CaseExecutionId: " + caseExecution.getId()));

        caseExecutionList.stream().filter(c -> c.isAvailable())
                .filter(c -> c.getActivityType().compareTo("milestone") != 0)
                .forEach(caseExecution -> System.out.println("Impossible to start ('available'): "
                        + caseExecution.getActivityName() + " [" + caseExecution.getActivityType() + "]"
                        + " - CaseExecutionId: " + caseExecution.getId()));

        caseExecutionList.stream().filter(c -> c.isAvailable())
                .filter(c -> c.getActivityType().compareTo("milestone") == 0)
                .forEach(caseExecution -> System.out.println("Milestone not reached yet: "
                        + caseExecution.getActivityName() + " - CaseExecutionId: " + caseExecution.getId()));

        System.out.println("---------------------------------------------");
        System.out.println();
    }
}
