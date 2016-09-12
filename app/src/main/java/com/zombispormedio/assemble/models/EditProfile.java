package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class EditProfile {

    public String username;

    public String bio;

    public String location;

    public String birth_date;

    public EditProfile(){

    }

    public EditProfile(String username, String bio, String location, String birth_date) {
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.birth_date = birth_date;
    }


    public static class Builder {

        private Profile profile;

        private String username;

        private String bio;

        private String location;

        private String birth_date;

        public Builder(Profile profile) {
            this.profile = profile;
            fields();
        }

        private void fields() {
            username = profile.username;
            bio = profile.bio;
            location = profile.location;
            birth_date = profile.birth_date;
        }

        public Builder() {
            profile = null;
            username = null;
            bio = null;
            location = null;
            birth_date = null;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
            fields();
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setBirthDate(String birth_date) {
            this.birth_date = birth_date;
        }

        public String getBirthdate() {
            return birth_date;
        }

        public boolean hasChanged() {
            boolean changed = false;

            if (!username.equals(profile.username) ||
                    !bio.equals(profile.bio) ||
                    !location.equals(profile.location) ||
                    !birth_date.equals(profile.birth_date)
                    ) {
                changed = true;
            }

            return changed;
        }

        public String getLocation() {
            return location;
        }

        public String getBio() {
            return bio;
        }

        public String getUsername() {
            return username;
        }

        public EditProfile build() {
            if (username == null) {
                username = profile.username;
            }

            if (bio == null) {
                bio = profile.bio;
            }

            if (location == null) {
                location = profile.location;
            }

            if (birth_date == null) {
                birth_date = profile.birth_date;
            }

            return new EditProfile(username, bio, location, birth_date);
        }
    }
}
