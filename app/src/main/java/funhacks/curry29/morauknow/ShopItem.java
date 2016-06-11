package funhacks.curry29.morauknow;

/**
 * Created by b1014100 on 2016/06/11.
 */
public class ShopItem {
    private int EventId;
    private String EventName;
    private String EventDetail;
    private String Connection;
    private String ShopName;
    private String Time;
    private int AreaId;
    private String Area;
    private int ImageURL;
    /*セッター*/
    public int getEventId(){return EventId;}
    public String getEventName(){return EventName;}
    public String getEventDetail(){return EventDetail;}
    public String getConnection(){return Connection;}
    public String getShopName(){return ShopName;}
    public String getTime(){return Time;}
    public int getAreaId(){return AreaId;}
    public  String getArea(){return  Area;}
    public int getImageURL(){return ImageURL;}

    /*ゲッター*/
    public void setEventId(int eventId){this.EventId = eventId;}
    public void setEventName(String eventName){this.EventName = eventName;}
    public void setEventDetail(String EventDetail){this.EventDetail = EventDetail;}
    public void setConnection(String connection){this.Connection = connection;}
    public void setShopName(String shopName){this.ShopName = shopName;}
    public void setTime(String time){this.Time =time;}
    public void setArea(String area){this.Area = area;}
    public void setAreaId(int areaId){this.AreaId = areaId;}
    public void setImageURL(int imageURL){this.ImageURL = imageURL;}
}
