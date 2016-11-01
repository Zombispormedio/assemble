package com.zombispormedio.assemble.models.editors;

import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class TeamEditor {

    public final String name;

    public final String description;

    public final int[] members;

    public final int admin;

    public TeamEditor(String name, String description, int[] members, int admin) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.admin = admin;
    }

    public static class Builder {

        private String name;

        private String description;

        private int[] members;

        private int admin;

        public Builder() {
            name = description = "";
        }

        @NonNull
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        @NonNull
        public Builder setMembers(int[] members) {
            this.members = members;
            return this;
        }

        @NonNull
        public Builder setAdmin(int admin) {
            this.admin = admin;
            return this;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int[] getMembers() {
            return members;
        }

        public int getAdmin() {
            return admin;
        }

        @NonNull
        public TeamEditor build() {
            return new TeamEditor(name, description, members, admin);
        }


    }
}
