package anugerah.gmi.com.model;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by christian.simon on 04/11/2016.
 */
public class LogosViewModel implements GeneralModel{

    private String id;
    private String name;
    private String imageUrl;
    private String contentUrl;
    private Integer active;
    private Date updatedDate;
    private Date createdDate;

    public LogosViewModel(){}

    public LogosViewModel(JSONObject object) throws Exception{
        this.id = object.get("id").toString();
        this.contentUrl = object.get("content_url").toString();
        this.imageUrl = object.get("image_url").toString();
        this.active = Integer.parseInt(object.get("active").toString());
        //this.updatedDate = new Date(object.get("updated_date").toString());
        //this.createdDate = new Date(object.get("created_date").toString());
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setContentUrl(String contentUrl){
        this.contentUrl = contentUrl;
    }

    public void setActive(int active){
        this.active = active;
    }

    public void setUpdatedDate(Date date){
        this.updatedDate = date;
    }


    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getContentUrl(){
        return contentUrl;
    }

    public int getActive(){
        return active;
    }

    public Date getUpdatedDate(){
        return updatedDate;
    }

}
