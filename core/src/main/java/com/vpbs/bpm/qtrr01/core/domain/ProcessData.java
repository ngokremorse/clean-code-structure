package com.vpbs.bpm.qtrr01.core.domain;

import com.vpbs.bpm.qtrr01.core.enums.CheckerActionEnum;
import com.vpbs.bpm.qtrr01.core.enums.MakerActionEnum;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ProcessData {
    private String title;
    private String requestUser;
    private String department;
    private String approveUser;
    private String customerName;
    private Long requestDate;
    private String checkerNote;
    private String checkerReason;
    private MakerActionEnum makerAction;
    private CheckerActionEnum checkerAction;
    private List<ApproverDto> approvers;
    private List<String> viewers;
    private List<DocumentSignDto> documentSigns;
    private List<String> documentSignSort;
    private List<String> documentAttachSort;

    public Map<String, Object> toMap() throws IllegalAccessException {
        Map<String, Object> processData = new HashMap<>();
        Field[] declaredFields = getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            Object value = f.get(this);
            if (value == null) continue;
            if (value instanceof Enum<?>) {
                processData.put(f.getType().getSimpleName(), ((Enum<?>) value).name());
            } else {
                processData.put(f.getType().getSimpleName(), value);
            }
        }
        return processData;
    }
}
