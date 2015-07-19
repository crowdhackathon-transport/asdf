package nkparadox.bussightseeing;

/**
 * Created by nkparadox on 7/18/15.
 */
public class routeItem {
    private String routeShortName;
    private String routeLongName;

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }


    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    @Override
    public String toString() {
        return  routeShortName +" : "+routeLongName ;
    }
}
