package com.example.tyler.assignmentrestaurant;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * Created by Tyler on 4/05/2017.
 */

public abstract class XMLHandle {
    private String error = "none";
    public String getError() { return error; }
    private Context context;

    public XMLHandle() {
    }

    public XMLHandle(Context pContext) {
        setContext(pContext);
    }

    protected List parse(InputStream is){
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            parser.nextTag();
            return readRoot(parser);
        }
        catch (XmlPullParserException e) {
            error = "Unable to parse file";
        }
        catch (IOException e) {
            error = "Can not read given file";
        }

        //return null if an error has happened
        return null;
    }

    //if using assets
    protected List parseAsset(String assetName) {
        if (context != null)
        {
            try {
                InputStream is = context.getAssets().open(assetName);
                return parse(is);
            }
            catch (IOException e) {
                error = "Unable to open asset file";
            }
        }
        else
            error = "Can not invoke parseAsset() : no context has been set, use setContext() first";

        return null;
    }

    protected abstract List readRoot(XmlPullParser parser) throws XmlPullParserException, IOException;

    public abstract String createXML(Object object) throws IllegalArgumentException, IllegalStateException, IOException;

    //returns all the read data from the tag
    protected String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tag);
        return title;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    protected void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public void setContext(Context pContext) {
        context = pContext;
    }
}
