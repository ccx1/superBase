package com.example.administrator.superbase.view.bottom;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_chicunxiang on 2018/2/9.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/9 17:15
 */

class TabParser {

    private static final String TAB_TAG = "tab";
    private static final int AVG_NUMBER_OF_TABS = 5;
    private final Context           mContext;
    private final XmlResourceParser parser;
    private List<BottomTab> tabs;

    TabParser(@NonNull Context context, int tab_xmlRes) {
        this.mContext = context;
        this.parser = context.getResources().getXml(tab_xmlRes);
    }
    @CheckResult
    @NonNull
    List<BottomTab> parseTabs() {
        if (tabs == null) {
            tabs = new ArrayList<>(AVG_NUMBER_OF_TABS);
            try {
                int eventType;
                do {
                    eventType = parser.next();
                    if (eventType == XmlResourceParser.START_TAG && TAB_TAG.equals(parser.getName())) {
                        BottomTab bottomBarTab = parseNewTab(parser, tabs.size());
                        tabs.add(bottomBarTab);
                    }
                } while (eventType != XmlResourceParser.END_DOCUMENT);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
                throw new RuntimeException("IOException | XmlPullParserException is Fail");
            }
        }

        return tabs;

    }

    private BottomTab parseNewTab(XmlResourceParser parser, int size) {

        return null;
    }
}
