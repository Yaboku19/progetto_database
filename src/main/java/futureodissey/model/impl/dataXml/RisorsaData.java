package futureodissey.model.impl.dataXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "risorsa")
@XmlAccessorType (XmlAccessType.FIELD)
public class RisorsaData {
    @XmlElement
    private String name;

    public String getName() {
        return name;
    }
}

