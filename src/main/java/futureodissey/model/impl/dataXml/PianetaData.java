package futureodissey.model.impl.dataXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "pianeta")
@XmlAccessorType (XmlAccessType.FIELD)
public class PianetaData {
    @XmlElement
    private String name;
    @XmlElement
    private String risorsa;

    public String getName() {
        return name;
    }

    public String getRisorsa() {
        return risorsa;
    }
}