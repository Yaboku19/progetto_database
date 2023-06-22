package futureodissey.model.impl.dataXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "richiesta")
@XmlAccessorType (XmlAccessType.FIELD)
public class RichiestaData {
    @XmlElement
    private int codice;
    @XmlElement
    private String risorsa;
    @XmlElement
    private int quantita;

    public int getCodice() {
        return codice;
    }

    public String getRisorsa() {
        return risorsa;
    }

    public int getQuantita() {
        return quantita;
    }
}
