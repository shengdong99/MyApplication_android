package com.shengdong.recyclerviewtest.Model;

public class ItemList {

    private String ropatitle;
    private int imageId;

    public ItemList(String ropatitle, int imageId) {
        this.ropatitle = ropatitle;
        this.imageId = imageId;
    }

    public String getRopatitle() {
        return ropatitle;
    }

    public void setRopatitle(String ropatitle) {
        this.ropatitle = ropatitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
