package com.cucumbercraft.POMPages.BuyNow;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BuyNowContext {

    private static BuyNowContext instance;
    private String username;

    private BuyNowContext() {
    }

    public static synchronized BuyNowContext getInstance() {
        if (instance == null) {
            instance = new BuyNowContext(); // Create the instance if it doesn't exist
        }
        return instance; // Return the single instance
    }

    private boolean isJoint, isSole, isGuest, isSignIn, onlyPB, containsPBAndPBGift, CDEPrimaryFlag, CDESecondaryFlag;
    private String expectedPrimaryName, expectedSecondaryName, orderRefNo;
    private Map<String, String> userMapSole;
    private Map<String, String> userMapJoint;
}
