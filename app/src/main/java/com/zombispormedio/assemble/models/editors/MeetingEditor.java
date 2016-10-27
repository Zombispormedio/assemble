package com.zombispormedio.assemble.models.editors;

import com.zombispormedio.assemble.utils.ISODate;

/**
 * Created by Xavier Serrano on 28/09/2016.
 */

public class MeetingEditor {

    public final String name;

    public final String description;

    public final int team;

    public final String start_at;

    public final String end_at;


    public MeetingEditor(String name, String description, int team, String start_at, String end_at) {
        this.name = name;
        this.description = description;
        this.team = team;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public static class Builder {
        private String name;

        private String description;

        private int team;

        private final ISODate startAt;

        private final ISODate endAt;

        private boolean allDay;

        public Builder() {
            name = description = "";
            startAt=ISODate.Now();
            endAt=ISODate.Now();
            initDates();
            allDay=false;
        }

        private void initDates() {
            startAt.setHour(endAt.getHour()+1);
            startAt.setMinutes(0);
            endAt.setHour(endAt.getHour()+2);
            endAt.setMinutes(0);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setTeam(int team) {
            this.team = team;
            return this;
        }

        public boolean isAllDay() {
            return allDay;
        }

        public ISODate getStartAt() {
            return startAt;
        }

        public ISODate getEndAt() {
            return endAt;
        }

        public void setAllDay(boolean allDay) {
            this.allDay = allDay;
        }

        private void resetHours(ISODate d){
            d.setMinutes(0);
            d.setHour(0);
        }

        public MeetingEditor build(){
            if(allDay){
                resetHours(startAt);
                resetHours(endAt);
            }
            return new MeetingEditor(name, description, team, startAt.toString(), endAt.toString());
        }
    }


}
