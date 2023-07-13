package com.kaizen.aop;

public final class UserEventDescriptionBuilder {
    private int id;
    private String userName;
    private String userLastname;
    private int userBrigade;

    private UserEventDescriptionBuilder(int id, String userName, String userLastname, int userBrigade) {
        this.id = id;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userBrigade = userBrigade;
    }

    public static class Builder {

        private int id;
        private String userName;
        private String userLastname;
        private int userBrigade;

        public Builder eventId (int id) {
            this.id = id;
            return this;
        }


        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userLastname(String userLastname) {
            this.userLastname = userLastname;
            return this;
        }

        public Builder userBrigade(int userBrigade) {
            this.userBrigade = userBrigade;
            return this;
        }

        public UserEventDescriptionBuilder build() {
            return new UserEventDescriptionBuilder(id,userName,
                    userLastname,userBrigade);
        }
    }

    @Override
    public String toString() {
        return id +
                ", ='" + userName + '\'' +
                ", ='" + userLastname + '\'' +
                ", =" + userBrigade;
    }
}