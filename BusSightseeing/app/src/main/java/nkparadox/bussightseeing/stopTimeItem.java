package nkparadox.bussightseeing;

/**
 * Created by nkparadox on 7/18/15.
 */
public class stopTimeItem {
    private String routeShortName;
    private int stopId;
    private int sequence;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    @Override
    public String toString() {
        return "stopTimeItem{" +
                "routeShortName='" + routeShortName + '\'' +
                ", stopId=" + stopId +
                ", sequence=" + sequence +
                '}';
    }
}
