import java.util.ArrayList;

public class Engine {

    ArrayList<EngineObject> objects = new ArrayList<>();

    Engine() {

    }


    public ArrayList<EngineObject> search(Query q) {
        Engine result = new Engine();
        for (int i = 0; i < this.objects.size(); i++) {
            if (this.objects.get(i).match(q) == 0) {
                result.objects.add(this.objects.get(i));
            }
        }
        return result.objects;
    }

    public void add(EngineObject engineObject) {
        objects.add(engineObject);
    }
}