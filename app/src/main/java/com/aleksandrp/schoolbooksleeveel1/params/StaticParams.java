package com.aleksandrp.schoolbooksleeveel1.params;

/**
 * Created by Aleksandr on 20.12.2015.
 */
public class StaticParams {

    private static boolean enableButtonLoadFile = false;

    public static boolean isEnableButtonLoadFile() {
        return enableButtonLoadFile;
    }

    public static void setEnableButtonLoadFile(boolean enableButtonLoadFile) {
        StaticParams.enableButtonLoadFile = enableButtonLoadFile;
    }
}
