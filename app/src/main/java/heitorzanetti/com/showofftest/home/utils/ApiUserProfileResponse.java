package heitorzanetti.com.showofftest.home.utils;

import java.io.Serializable;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class ApiUserProfileResponse implements Serializable{

    Meta meta;
    Data data;
    Pagination pagination;


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
        String profile_picture;
        String id;
        String username;
        Counts counts;
        String bio;
        String website;
        String full_name;


        public String getProfile_picture() {
            return profile_picture;
        }

        public void setProfile_picture(String profile_picture) {
            this.profile_picture = profile_picture;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Counts getCounts() {
            return counts;
        }

        public void setCounts(Counts counts) {
            this.counts = counts;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
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


