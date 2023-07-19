package com.shsnc.bean;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueryCondition {
    protected String condition;
    protected String fileName;
    protected Collection<Long> recordIds;
    protected boolean exportTemplate;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void toRecordIdList(String idStrings) {
        if (StringUtils.isBlank(idStrings)) {
            return;
        }
        String[] ids = idStrings.split(",");
        List<Long> instanceIds = new ArrayList<Long>();
        for (String id : ids) {
            Long recordId = Long.valueOf(id);
            instanceIds.add(recordId);
        }
        setRecordIds(instanceIds);
    }
    public Collection<Long> getRecordIds() {
        return recordIds;
    }

    public void setRecordIds(Collection<Long> recordIds) {
        this.recordIds = recordIds;
    }

    public boolean isExportTemplate() {
        return exportTemplate;
    }

    public void setExportTemplate(boolean exportTemplate) {
        this.exportTemplate = exportTemplate;
    }
}
