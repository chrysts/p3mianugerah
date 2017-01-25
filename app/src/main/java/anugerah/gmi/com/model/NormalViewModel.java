package anugerah.gmi.com.model;

import org.json.JSONObject;

/**
 * Created by christian.simon on 29/08/2016.
 */
public class NormalViewModel implements GeneralModel{

    private String id;
    private String title;
    private String content;
    private String createdAt;
    private String type;
    private String user;
    private String verse;
    private String jempol;

    public NormalViewModel(){}

    public NormalViewModel(JSONObject obj) throws Exception{
        this.id = obj.get("id").toString();
        this.title = obj.get("title").toString();
        this.content = obj.get("content").toString();
        this.createdAt = obj.get("createdAt").toString();
        this.type = obj.get("type").toString();
        this.user = obj.get("user").toString();
        this.verse = obj.get("verse").toString();
        this.jempol = obj.get("jempol").toString();
    }

    public void setId(String id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public void setType(String type){
        this.type = type;
    }
    public void setUser(String user){
        this.user = user;
    }

    public void setVerse(String verse){
        this.verse = verse;
    }

    public void setJempol(String jempol){
        this.jempol = jempol;
    }


    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return  content;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String getType(){
        return type;
    }
    public String setUser(){
        return user;
    }

    public String getVerse(){
        return verse;
    }

    public String getJempol(){
        return jempol;
    }



}
