package futureodissey.model.impl.dataXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TaskTypeList")
@XmlAccessorType (XmlAccessType.FIELD)
public class TaskTypeList {
    @XmlElement(name = "taskType")
    private final List<TaskTypeData> taskType = new ArrayList<>();

    public List<TaskTypeData> getTaskType() {
        return new ArrayList<>(taskType);
    }
}
