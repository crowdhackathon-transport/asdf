package nkparadox.bussightseeing;


public class stopItem {
    private int id;
    private String stop;
    private Double lat;
    private Double lon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }
    

    public void setLon(Double lon) {
        this.lon = lon;
    }



    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return stop;
    }
}

