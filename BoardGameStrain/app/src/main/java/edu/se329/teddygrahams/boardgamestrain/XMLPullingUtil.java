package edu.se329.teddygrahams.boardgamestrain;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by wdben on 8/7/14.
 */
public class XMLPullingUtil {
    private String mTitle;
    private static final String ns = null;

    public  XMLPullingUtil(String url,  final OnItemParsedListener listener ){
        new AsyncTask<String, BoardGame, Integer>() {
            @Override
            protected Integer doInBackground(String... params) {
                try {

//                    InputStream str = generateInputStream(params[0]);
                    InputStream str = readInputStreamFile();
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(str, null);
                    parser.nextTag();

                    parser.require(XmlPullParser.START_TAG, ns, "rss");

                    while (parser.next() != XmlPullParser.END_TAG) {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            continue;
                        }
                        String name = parser.getName();
                        // Starts by looking for the entry tag
                        if(name.equals("channel")) {
                            continue;
                        }

                        if (name.equals("item")) {
                            publishProgress(readEntry(parser));
                        } else if(name.equals("name")) {
                            mTitle = readTag(parser, "name");
                        }else {
                            skip(parser);
                        }

                    }
                    str.close();

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            }

            @Override
            protected void onProgressUpdate(BoardGame... values) {
                listener.itemParsed(values[0]);
                super.onProgressUpdate(values);
            }
        }.execute(url);


    }

    public String getTitle(){
        return mTitle;
    }

    private InputStream readInputStreamFile(){
        try {
            InputStream stream = new FileInputStream(new File("games.xml"));
            return stream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
return null;
    }

    private InputStream generateInputStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }


    private BoardGame readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        String title = null;
        int min = 0;
        int max = 0;
        int length = 0;
        int rating = 0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTag(parser, "title");
                Log.i("Name",title);
            } else if (name.equals("minplayers")) {
                min = Integer.parseInt(readTag(parser, "minplayers"));
                Log.i("Min","Min: "+min);
            } else if (name.equals("maxplayers")) {
                max = Integer.parseInt(readTag(parser, "maxplayers"));
                Log.i("Max","Max: "+max);
            } else if (name.equals("length")) {
                length = Integer.parseInt(readTag(parser, "length"));
            } else if (name.equals("rating")) {
                length = Integer.parseInt(readTag(parser, "rating"));
            } else {
                skip(parser);
            }
        }
        return new BoardGame(title,min,max,length,rating);
    }

    // Processes title tags in the feed.
    private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
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

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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
}

interface OnItemParsedListener {
        public void itemParsed(BoardGame game);

 }
