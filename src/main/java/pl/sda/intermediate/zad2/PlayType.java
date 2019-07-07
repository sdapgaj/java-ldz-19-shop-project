package pl.sda.intermediate.zad2;

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
