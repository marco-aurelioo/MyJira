package com.tiozao.tasks.aplication.controllers.model;

import java.util.List;

public class ProjectConfiguration {

    private List<ConfigurationStep> configurationStepList;
    private List<TaskType> taskTypeList;

    public List<TaskType> getTaskTypeList() {
        return taskTypeList;
    }

    public void setTaskTypeList(List<TaskType> taskTypeList) {
        this.taskTypeList = taskTypeList;
    }

    public List<ConfigurationStep> getConfigurationStepList() {
        return configurationStepList;
    }

    public void setConfigurationStepList(List<ConfigurationStep> configurationStepList) {
        this.configurationStepList = configurationStepList;
    }
}
