package funhacks.curry29.morauknow;

/**
 * Created by b1014100 on 2016/05/25.
 */
public class ListItem {

    private int imageId;
    private String text;

    private int EventId;
    private String EventName;
    private String Time;
    private int AreaId;
    private String Area;
    private int ImageURL;
    /*セッター*/
    public int getImageId(){
        return imageId;
    }

    public String getText(){
        return text;
    }

    public int getEventId(){return EventId;}
    public String getEventName(){return EventName;}
    public String getTime(){return Time;}
    public int getAreaId(){return AreaId;}
    public  String getArea(){return  Area;}
    public int getImageURL(){return ImageURL;}

    /*ゲッター*/
    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setEventId(int eventId){this.EventId = eventId;}
    public void setEventName(String eventName){this.EventName = eventName;}
    public void setTime(String time){this.Time =time;}
    public void setArea(String area){this.Area = area;}
    public void setAreaId(int areaId){this.AreaId = areaId;}
    public void setImageURL(int imageURL){this.ImageURL = imageURL;}

}
