package swipefit.utilities;

import java.util.HashMap;

public class Data {
    private static HashMap<String,String> userBehaviour = new HashMap<>();
    private static boolean INITIAL_DATA_FLAG = true;

    public static boolean isInitialDataFlag() {
        return INITIAL_DATA_FLAG;
    }

    public static void setInitialDataFlag(boolean initialDataFlag) {
        INITIAL_DATA_FLAG = initialDataFlag;
    }

    public static HashMap<String, String> getUserBehaviour() {
        return userBehaviour;
    }

    public static void setUserBehaviour(HashMap<String, String> behaviour) {
        userBehaviour = behaviour;
    }
}
