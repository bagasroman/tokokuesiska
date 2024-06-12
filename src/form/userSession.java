package form;

class userSession {
    
    private static String userLogin;

    public static void setUserLogin(String userLogin) {
        userSession.userLogin = userLogin;
    }
    
    
    
    
    
    
    public static String getUserLogin() {
        return userLogin;
    }
}