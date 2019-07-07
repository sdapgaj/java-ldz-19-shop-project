package pl.sda.intermediate.zad2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Playlist extends Playable {

    private PlayType playType = PlayType.SEQUENCE;
    private List<Playable> elements = new ArrayList<>();

    public void AddToPlaylist(Playable playList) {
        elements.add(playList);
    }

    public void choosePlayType(PlayType playType) {
        this.playType = playType;
    }

    @Override
    public String play() {

        switch (playType) {
            case SEQUENCE:
                return playOnce(elements);
            case LOOP:
                return playLoop();
            case RANDOM:
                List<Playable> copy = new ArrayList<>(elements);
                Collections.shuffle(copy);
                return playOnce(copy);
            default:
                return "Choose play TYpe";
        }

    }

    private String playLoop() {
        return IntStream.rangeClosed(1,11)
                .mapToObj(i -> playOnce(elements))
                .collect(Collectors.joining("\n--------\n"));
    }

    private String playOnce(List<Playable> list) {
        return list.stream()
                .map(e -> e.play())
                .collect(Collectors.joining("\n"));
    }

}
