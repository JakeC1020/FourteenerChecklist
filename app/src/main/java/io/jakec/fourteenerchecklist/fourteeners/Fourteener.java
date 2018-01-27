package io.jakec.fourteenerchecklist.fourteeners;


public class Fourteener {
    private int id;
    private String name;
    private String range;
    private int elevation;

    public Fourteener(int id, String name, String range, int elevation) {
        this.id = id;
        this.name = name;
        this.range = range;
        this.elevation = elevation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}
