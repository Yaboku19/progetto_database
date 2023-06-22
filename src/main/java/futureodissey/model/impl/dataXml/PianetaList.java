package futureodissey.model.impl.dataXml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PianetaList")
@XmlAccessorType (XmlAccessType.FIELD)
public class PianetaList {
    @XmlElement(name = "pianeta")
    private final List<PianetaData> richiesta = new ArrayList<>();

    public List<PianetaData> getPianeta() {
        return new ArrayList<>(richiesta);
    }
}
