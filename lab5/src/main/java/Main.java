import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Main {
    public static void main(String[] args) {
        try {
            Person person = new Person("Sergey", "RUS", 32);
            Worker worker = new Worker(person, "Shop assistant");

            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer;
            writer = new XMLWriter(System.out, format);
            writer.write(XmlConverter.XMLFile(person));

            writer.write(XmlConverter.XMLFile(worker));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
