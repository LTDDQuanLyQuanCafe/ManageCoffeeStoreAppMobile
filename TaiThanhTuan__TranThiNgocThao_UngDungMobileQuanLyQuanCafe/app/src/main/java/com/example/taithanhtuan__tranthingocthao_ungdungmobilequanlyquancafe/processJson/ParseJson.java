package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson;

import android.net.http.HttpResponseCache;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseJson {

    public ParseJson(){}

    public static String StringFileContent;
    public static boolean StringFileIsDownloaded;

    public String readStringFileContent(String urlString)
    {
        StringFileContent = "";
        StringFileIsDownloaded = false;
        try
        {
            URL Url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)Url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader in = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            StringFileContent = response.toString();
            StringFileIsDownloaded = true;
        }
        catch (Exception localException)
        {
            System.out.println("Exception: " + localException.getMessage());
        }
        return StringFileContent;
    }
}
