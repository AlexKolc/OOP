import annotations.XmlObject;
import annotations.XmlTag;

@XmlObject
public class Worker {
    @XmlTag
    private Person person;
    private String work;

    Worker(Person person, String work) {
        this.person = person;
        this.work = work;
    }

    @XmlTag
    public String getWork() {
        return work;
    }
}
