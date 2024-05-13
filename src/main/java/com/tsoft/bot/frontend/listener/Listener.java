package com.tsoft.bot.frontend.listener;

import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.FileHelper;

import java.util.logging.Logger;

public class Listener {

    private boolean deleteFiles = false;

    protected void onTestStart(String name) {
        try {
            ExtentReportUtil.INSTANCE.createTest(name);
        } catch (Exception e) {
            Logger.getLogger("[ERROR CRL-4213] Error en onTestStart: " + e.getMessage());
        }
    }

    public void onStart() {
        if (!deleteFiles) {
            FileHelper.deleteFolderElements("/img");
            deleteFiles = true;
        }
    }

    protected void onFinish() {
        ExtentReportUtil.INSTANCE.flushReport();
    }
}
