package rainmtime.com.demorepo.orm.litepal.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chunyu on 2018/2/24 下午4:58.
 * Email: 746431278@qq.com
 */

public class Album extends DataSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    private String cover;

    private List<Song> songs = new ArrayList<Song>();

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
