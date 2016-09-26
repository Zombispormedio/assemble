package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 26/09/2016.
 */

public class EditTeam {

    public String name;

    public String description;

    public int[] members;

    public int admin;

    public EditTeam(String name, String description, int[] members, int admin) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.admin = admin;
    }

    public static class Builder {
        public String name;

        public String description;

        public int[] members;

        public int admin;

        public Builder() {
            name=description="";
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setMembers(int[] members) {
            this.members = members;
            return this;
        }

        public Builder setAdmin(int admin) {
            this.admin = admin;
            return this;
        }

        public EditTeam build(){
            return new EditTeam(name, description, members, admin);
        }


    }
}
