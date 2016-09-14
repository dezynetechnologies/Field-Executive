package in.dezyne.fieldexecutive;

/**
 * Created by Dezyne 2 on 9/14/2016.
 */
public class LocationGetSet {


    String _location;
    String _timestamp;
    String _userid;

    public LocationGetSet() {
    }

    public LocationGetSet(String userid,String location,String timestamp )
    {
        this._location = location;
        this._timestamp = timestamp;
        this._userid = userid;
    }

    public String getlocation()
    {
        return this._location;
    }
    public void setlocation(String location)
    {
        this._location=location ;
    }

    public String gettimestamp()
    {
        return this._timestamp;
    }
    public void settimestamp(String timestamp)
    {
        this._timestamp=timestamp ;
    }

    public String getuserid()
    {
        return this._userid;
    }
    public void setuserid(String userid)
    {
        this._userid=userid ;
    }
}
