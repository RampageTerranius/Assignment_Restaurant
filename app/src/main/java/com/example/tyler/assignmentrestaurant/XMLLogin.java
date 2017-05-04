package com.example.tyler.assignmentrestaurant;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 4/05/2017.
 */

public class XMLLogin extends XMLHandle {
    @Override
    public List readRoot(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, null, "Login");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the person tag
            if (name.equals("User")) {
                entries.add(readLogin(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    @Override
    public String createXML(Object object) throws IllegalArgumentException, IllegalStateException, IOException
    {
        Login person = (Login)object;

        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();
        xmlSerializer.setOutput(stringWriter);

        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

        xmlSerializer.startTag(null, "UserName");
        xmlSerializer.text(person.getUser());
        xmlSerializer.endTag(null, "UserName");

        xmlSerializer.startTag(null, "Password");
        xmlSerializer.text(person.getPassword());
        xmlSerializer.endTag(null, "Password");

        xmlSerializer.endTag(null, "Login");

        xmlSerializer.endDocument();
        return stringWriter.toString();
    }

    // Parses the contents of a person. If it encounters a name or age tag, hands them off
    // to their respective "read" methods for processing. Otherwise, skips the tag.
    private Login readLogin(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "User");
        String user = null;
        String password = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if (tagName.equals("UserName")) {
                user = readTag(parser, "UserName");
            } else if (tagName.equals("Password")) {
                password = readTag(parser, "Password");
            }
            else {
                skip(parser);
            }
        }
        return new Login(user, password);
    }
}
