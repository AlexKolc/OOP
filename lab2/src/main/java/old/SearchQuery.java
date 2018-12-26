package old;

import old.Track;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SearchQuery {
    /*private List<Track> currentTracks;

    public SearchQuery(List<Track> initialSongs) {
        this.currentTracks = initialSongs;
    }

    /**
     * Поиск по имени исполнителя
     *
     * @param author – target author
     * @return Экземпляр SearchQuery, содержащий песни, подходящего автора.

    public SearchQuery author(String author) {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .filter(track -> track.getAuthor().equals(author) || track.getFeat().contains(author))
                        .collect(toList())
        );
    }

    /**
     * Поиск по названию альбома
     *
     * @param albumName – target album name
     * @return Экземлпяр SearchQuery, содержащий песни, подходящего альбома.

    public SearchQuery album(String albumName) {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .filter(track -> track.getAlbum().equals(albumName))
                        .collect(toList())
        );
    }

    /**
     * Поиск песен по названию жанра
     *
     * @param genre – target genre
     * @return Экземлпяр SearchQuery, содержащий песни, подходящего жанра.

    public SearchQuery genre(String genre) {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .filter(track -> track.getGenre().contains(genre))
                        .collect(toList())
        );
    }

    /**
     * Поиск песен по их названию.
     * @param title – target song title
     * @return Экземпляр SearchQuery, содержащий песни, подходящего названия.

    public SearchQuery songs(String title) {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .filter(track -> track.getTitle().equals(title))
                        .collect(toList()));
    }

    /**
     * Поиск песен по году выпуска.
     * @param year
     * @return Экземпляр SearchQuery, содержащий песни, подходящего года.

    public SearchQuery year(String year) {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .filter(track -> track.getYear().equals(year))
                        .collect(toList()));
    }

    public SearchQuery all() {
        return new SearchQuery(
                immutableParallelStreamSongs()
                        .collect(toList())
        );
    }

    private Stream<Track> immutableParallelStreamSongs() {
        return currentTracks
                .parallelStream()
                .map(Track::clone);
    }

    public List results() {
        return currentTracks;
    }*/
}