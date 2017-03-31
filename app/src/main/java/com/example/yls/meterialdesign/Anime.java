package com.example.yls.meterialdesign;

/**
 * Created by icarus9527 on 2017/3/31.
 */

public class Anime {
    private String animeName;
    private int animePic;

    public Anime(String animeName,int animePic){
        this.animeName = animeName;
        this.animePic = animePic;
    }

    public int getAnimePic() {
        return animePic;
    }

    public void setAnimePic(int animePic) {
        this.animePic = animePic;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }
}
