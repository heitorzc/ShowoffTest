package heitorzanetti.com.showofftest.home.utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class ApiUserMediaResponse implements Serializable{

    Meta meta;
    ArrayList<Data> data;
    Pagination pagination;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public class Meta{
        int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }


    public class Data{
        boolean user_has_liked;
        Images images;
        Likes likes;
        Caption caption;
        String type;


        public boolean isUser_has_liked() {
            return user_has_liked;
        }

        public void setUser_has_liked(boolean user_has_liked) {
            this.user_has_liked = user_has_liked;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public Likes getLikes() {
            return likes;
        }

        public void setLikes(Likes likes) {
            this.likes = likes;
        }

        public Caption getCaption() {
            return caption;
        }

        public void setCaption(Caption caption) {
            this.caption = caption;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    public class Images{
        StandardResolution standard_resolution;

        public StandardResolution getStandard_resolution() {
            return standard_resolution;
        }

        public void setStandard_resolution(StandardResolution standard_resolution) {
            this.standard_resolution = standard_resolution;
        }
    }


    public class StandardResolution{
        int height;
        int width;
        String url;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public class Likes{
        int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    public class Caption{
        String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


    public class Counts {
        int followed_by;
        int follows;
        int media;

        public int getFollowed_by() {
            return followed_by;
        }

        public void setFollowed_by(int followed_by) {
            this.followed_by = followed_by;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getMedia() {
            return media;
        }

        public void setMedia(int media) {
            this.media = media;
        }
    }


    public class Pagination{
        String next_url;
        String next_max_id;

        public String getNext_url() {
            return next_url;
        }

        public void setNext_url(String next_url) {
            this.next_url = next_url;
        }

        public String getNext_max_id() {
            return next_max_id;
        }

        public void setNext_max_id(String next_max_id) {
            this.next_max_id = next_max_id;
        }
    }


}


