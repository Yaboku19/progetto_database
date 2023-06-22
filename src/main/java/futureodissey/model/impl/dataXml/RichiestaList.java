package futureodissey.model.impl.dataXml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RichiestaList")
@XmlAccessorType (XmlAccessType.FIELD)
public class RichiestaList {
    @XmlElement(name = "richiesta")
    private final List<RichiestaData> richiesta = new ArrayList<>();

    public List<RichiestaData> getRichiesta() {
        return new ArrayList<>(richiesta);
    }
}
