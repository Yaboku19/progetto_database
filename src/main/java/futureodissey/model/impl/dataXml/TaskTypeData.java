package futureodissey.model.impl.dataXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "risorsa")
@XmlAccessorType (XmlAccessType.FIELD)
public class TaskTypeData {
    @XmlElement
    private int codice;
    @XmlElement
    private String descrizione;
    @XmlElement
    private int numPersone;
    @XmlElement
    private int tempo;

    public int getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getNumPersone() {
        return numPersone;
    }

    public int getTempo() {
        return tempo;
    }
}
