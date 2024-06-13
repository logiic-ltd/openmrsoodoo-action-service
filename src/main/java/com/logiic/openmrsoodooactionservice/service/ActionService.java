package com.logiic.openmrsoodooactionservice.service;

import com.logiic.openmrsoodooactionservice.model.Action;
import com.logiic.openmrsoodooactionservice.model.Criteria;
import com.logiic.openmrsoodooactionservice.repository.CriteriaRepository;
import com.logiic.openmrsoodooactionservice.service.ExecutionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActionService {

    private final Map<String, Action> actions = new HashMap<>();
    private final CriteriaRepository criteriaRepository;
    private final ExecutionLogService executionLogService;

    @Autowired
    public ActionService(List<Action> actionList, CriteriaRepository criteriaRepository, ExecutionLogService executionLogService) {
        for (Action action : actionList) {
            actions.put(action.getClass().getSimpleName(), action);
        }
        this.criteriaRepository = criteriaRepository;
        this.executionLogService = executionLogService;
    }

    public void executeAction(String actionName, Map<String, Object> data, int eventId) throws Exception {
        Action action = actions.get(actionName);
        if (action != null) {
            List<Criteria> criteriaList = criteriaRepository.findByActionId(action.getId());
            boolean shouldExecute = evaluateCriteria(criteriaList, data);
            if (shouldExecute) {
                action.execute(data);
                executionLogService.logExecution(action.getId(), eventId, "Success", "Action executed successfully");
            } else {
                executionLogService.logExecution(action.getId(), eventId, "Skipped", "Criteria not met");
            }
        } else {
            throw new IllegalArgumentException("No such action: " + actionName);
        }
    }

    private boolean evaluateCriteria(List<Criteria> criteriaList, Map<String, Object> data) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        for (Criteria criteria : criteriaList) {
            String expression = criteria.getExpression();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                engine.put(entry.getKey(), entry.getValue());
            }
            if (!(boolean) engine.eval(expression)) {
                return false;
            }
        }
        return true;
    }
}