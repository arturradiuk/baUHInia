package login;

public enum UserType {
    Administrator,
    General;

    public static UserType fromString(String string) {
        switch (string) {
            case "Administrator":
                return Administrator;
            default:
                return General;
        }
    }
}


