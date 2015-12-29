package com.aleksandrp.schoolbooksleeveel1.params;

/**
 * Created by Aleksandr on 20.12.2015.
 */
public class StaticParams {

    private static boolean enableButtonLoadFile = false;
    private static boolean proccessAsyn = false;

    public static boolean isProccessAsyn() {
        return proccessAsyn;
    }

    public static void setProccessAsyn(boolean proccessAsyn) {
        StaticParams.proccessAsyn = proccessAsyn;
    }

    public static boolean isEnableButtonLoadFile() {
        return enableButtonLoadFile;
    }

    public static void setEnableButtonLoadFile(boolean enableButtonLoadFile) {
        StaticParams.enableButtonLoadFile = enableButtonLoadFile;
    }
}
