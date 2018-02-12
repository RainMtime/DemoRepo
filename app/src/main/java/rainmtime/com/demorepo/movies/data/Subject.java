package rainmtime.com.demorepo.movies.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chunyu on 2018/2/11 下午3:37.
 * Email: 746431278@qq.com
 */
public class Subject {
    /**
     * rating : {"max":10,"average":7.8,"stars":"40","min":0}
     * genres : ["动画","短片","动作"]
     * title : 喜剧
     * casts : [{"alt":"https://movie.douban.com/celebrity/1048584/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/27393.jpg","large":"https://img3.doubanio
     * .com/img/celebrity/large/27393.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/27393.jpg"},"name":"绿川光","id":"1048584"},{"alt":"https://movie.douban.com/celebrity/1028508/",
     * "avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1361592268.1.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1361592268.1.jpg","medium":"https://img3.doubanio
     * .com/img/celebrity/medium/1361592268.1.jpg"},"name":"前田爱","id":"1028508"}]
     * collect_count : 3616
     * original_title : 喜劇
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1321065/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/52041.jpg","large":"https://img3.doubanio
     * .com/img/celebrity/large/52041.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/52041.jpg"},"name":"中泽一登","id":"1321065"}]
     * year : 2002
     * images : {"small":"https://img5.doubanio.com/spic/s3823996.jpg","large":"https://img5.doubanio.com/lpic/s3823996.jpg","medium":"https://img5.doubanio.com/mpic/s3823996.jpg"}
     * alt : https://movie.douban.com/subject/3731342/
     * id : 3731342
     */

    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("casts")
    @Expose
    private List<Cast> casts = null;
    @SerializedName("collect_count")
    @Expose
    private Integer collectCount;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("alt")
    @Expose
    private String alt;
    @SerializedName("id")
    @Expose
    private String id;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

