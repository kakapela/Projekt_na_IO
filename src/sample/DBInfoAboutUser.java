package sample;

public class DBInfoAboutUser {

    private static DBInfoAboutUser INSTANCE;
    private String currentUser;
    private String currentPassword;

    private DBInfoAboutUser(){

    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public static DBInfoAboutUser getInstance(){
        if(INSTANCE==null)
            INSTANCE= new DBInfoAboutUser();
        return INSTANCE;
    }

    public void setUser(String CurrentUser,String CurrentPassword){


        currentUser = CurrentUser;
        currentPassword = CurrentPassword;
    }
}






