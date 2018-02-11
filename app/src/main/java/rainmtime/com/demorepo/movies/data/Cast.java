package rainmtime.com.demorepo.movies.data;

/**
 * Created by chunyu on 2018/2/11 下午3:39.
 * Email: 746431278@qq.com
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast {

    /**
     * alt : https://movie.douban.com/celebrity/1048584/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/27393.jpg","large":"https://img3.doubanio.com/img/celebrity/large/27393.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/27393.jpg"}
     * name : 绿川光
     * id : 1048584
     */

    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("avatars")
    @Expose
    private Avatars avatars;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
