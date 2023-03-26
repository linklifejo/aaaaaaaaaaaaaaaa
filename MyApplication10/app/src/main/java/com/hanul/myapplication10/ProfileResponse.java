package com.hanul.myapplication10;

public class ProfileResponse {
    private int status;
    private boolean success;
    private String message;
    private List<CalendarPhotoResponse.CalendarPhoto> data;

    public int getStatus(){
        return status;
    }

    public boolean getSuccess(){
        return success;
    }

    public String getMessage(){
        return message;
    }

    public List<CalendarPhotoResponse.CalendarPhoto> getData(){
        return data;
    }

    public class Profile {
        private int useridx;
        private String photo;

        public int getUseridx() {
            return useridx;
        }

        public String getPhoto() {
            return photo;
        }
    }

}
