package login;

public enum UserType {
    Administrator,
    General,
    Unauthorized;

    public static UserType fromString(String string) {
        switch (string) {
            case "Administrator":
                return Administrator;
            case "General":
                return General;
            default:
                return Unauthorized;
        }
    }
}


