package vn.fis.cms.configs;

public class ServerConstants {
    private static boolean autoRegister = true;

    public static boolean isAutoRegister() {
        return autoRegister;
    }

    public static void setAutoRegister(boolean autoRegister) {
        ServerConstants.autoRegister = autoRegister;
    }
}
