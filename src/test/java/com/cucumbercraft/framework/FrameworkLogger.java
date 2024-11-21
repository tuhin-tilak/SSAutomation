package com.cucumbercraft.framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class FrameworkLogger {

    private FrameworkLogger() {
    }


    private static final Consumer<String> PASS = ($) -> ExtentCucumberAdapter.getCurrentStep().pass($);
    private static final Consumer<String> FAIL = ($) -> ExtentCucumberAdapter.getCurrentStep().fail($);
    private static final Consumer<String> SKIP = ($) -> ExtentCucumberAdapter.getCurrentStep().skip($);
    private static final Consumer<String> INFO = ExtentCucumberAdapter.getCurrentStep()::info;
    private static final Consumer<String> CONSOLE = System.out::println;
    private static final Consumer<String> EXTENTANDCONSOLEPASS = PASS.andThen(CONSOLE);
    private static final Consumer<String> EXTENTANDCONSOLEFAIL = FAIL.andThen(CONSOLE);


    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL);
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, System.out::println);
        MAP.put(LogType.EXTENTANDCONSOLEPASS, EXTENTANDCONSOLEPASS);
        MAP.put(LogType.EXTENTANDCONSOLEFAIL, EXTENTANDCONSOLEFAIL);
    }

    public static void log(LogType type, String message) {
        MAP.get(type).accept(message);
    }


}
