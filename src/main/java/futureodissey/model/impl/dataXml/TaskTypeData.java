package futureodissey.model.impl.dataXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "taskType")
@XmlAccessorType (XmlAccessType.FIELD)
public class TaskTypeData {
    @XmlElement
    private int codice;
    @XmlElement
    private String descrizione;
    @XmlElement
    private int numPersone;

    public int getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getNumPersone() {
        return numPersone;
    }
}
