package futureodissey.model.impl.dataXml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RisorseList")
@XmlAccessorType (XmlAccessType.FIELD)
public class RisorsaList {
    @XmlElement(name = "risorsa")
    private final List<RisorsaData> risorse = new ArrayList<>();

    public List<RisorsaData> getRisorse() {
        return new ArrayList<>(risorse);
    }
}
