package old;

import old.Loader;
import old.Track;
import org.json.JSONArray;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class TrackManager {
    /*private List<Track> tracks;

    private static final String SONG_DIRECTORY = "songs";

    public TrackManager(String managerName) {
        Path filePath = Loader.getFilePath(SONG_DIRECTORY + "/" + managerName + ".json");
        String fileContent = Loader.getFileContent(filePath);
        String jsonSongInfoArray = new JSONArray(fileContent);

        var items = jsonSongInfoArray.toList().stream()
                .map(info -> (Map) info)
                .map(Track::new)
                .collect(toList());

        this.tracks = new ArrayList<>(items);
    }

    public SearchQuery createSearchQuery() {
        return new SearchQuery(new ArrayList<>(this.tracks));
    }*/
}