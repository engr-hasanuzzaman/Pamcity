package com.iqrasys.pamcity.model;

public class RowItem {
    private int imageId;
    private String title;
    private String score;
 
//    public RowItem(int imageId, String title, String desc) {
//        this.imageId = imageId;
//        this.title = title;
//        this.desc = desc;
//    }
    public RowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
        
    }
    // for selectItemView page 
    public RowItem( String title) {
        
        this.title = title;
        
    }
    
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return score;
    }
    public void setDesc(String desc) {
        this.score = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title ;
    }
}
