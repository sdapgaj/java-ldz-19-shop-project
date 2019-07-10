package pl.sda.intermediate.playlista;

public enum PlayType {

    RANDOM("losowa"),
    SEQUENCE("kolejno"),
    LOOP("w pętli");

    private String plName;

    PlayType(String plName) {
        this.plName = plName;
    }

    public String getPlName() {
        return plName;
    }

}
