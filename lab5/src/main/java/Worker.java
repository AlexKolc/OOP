import annotations.XmlObject;
import annotations.XmlTag;

@XmlObject
public class Worker extends Person {
    @XmlTag
    private String work;

    Worker(String name, String lang, int age, String work) {
        super(name, lang, age);
        this.work = work;
    }

    @XmlTag(name = "ne work")
    public String getWork() {
        return work;
    }
}
