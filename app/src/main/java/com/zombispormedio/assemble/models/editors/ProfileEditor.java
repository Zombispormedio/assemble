package com.zombispormedio.assemble.models.editors;

import com.zombispormedio.assemble.models.Profile;

import android.support.annotation.Nullable;

/**
 * Created by Xavier Serrano on 23/08/2016.
 */
public class ProfileEditor {

    public String username;

    public String bio;

    public String location;

    public String birth_date;

    public ProfileEditor() {

    }

    public ProfileEditor(String username, String bio, String location, String birth_date) {
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.birth_date = birth_date;
    }


    public static class Builder {

        @Nullable
        private Profile profile;

        @Nullable
        private String username;

        @Nullable
        private String bio;

        @Nullable
        private String location;

        @Nullable
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

        @Nullable
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

        @Nullable
        public String getLocation() {
            return location;
        }

        @Nullable
        public String getBio() {
            return bio;
        }

        @Nullable
        public String getUsername() {
            return username;
        }

        @Nullable
        public ProfileEditor build() {
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

            return new ProfileEditor(username, bio, location, birth_date);
        }
    }
}
