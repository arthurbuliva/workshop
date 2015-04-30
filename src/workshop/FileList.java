/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop;

/**
 * @author bulivaa <arthur.buliva@unon.org>
 */
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;

/**
 * @author aaa
 *
 */
public class FileList
{

    public static String listFiles(String authTicket, String siteName)
    {
        StringBuilder listBuilder = new StringBuilder();

        try
        {
            URL alfresco = new URL("http://10.104.104.27:8080/alfresco/service/slingshot/doclib/doclist/folders/site/"
                    + siteName
                    + "/documentLibrary?alf_ticket="
                    + authTicket);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(alfresco.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                listBuilder.append(inputLine);
            }
            in.close();

//            curl
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return listBuilder.toString();
    }

    public static void main(String args[]) throws IOException
    {
        String username = "arthur";
        String password = "Lemonade";

        String alfrescoTiccketURL = "http://10.104.104.27:8080/alfresco"
                + "/service/api/login?u=" + username + "&pw=" + password;
        InteractiveAuthentication ticket = new InteractiveAuthentication();
        String ticketURLResponse = ticket.getTicket(alfrescoTiccketURL);

        String filesList = listFiles(ticketURLResponse, "document-manager");

        InputStream stream = new ByteArrayInputStream(filesList.getBytes(StandardCharsets.UTF_8));

        JsonReader jsonReader = Json.createReader(stream);

        //get JsonObject from JsonReader
        JsonObject jsonObject = jsonReader.readObject();

        //we can close IO resource and JsonReader now
        jsonReader.close();

        JsonArray data = (JsonArray) jsonObject.get("items");

        for (int i = 0; i < data.size(); i++)
        {
//            System.out.println(data.get(i));

            JsonObject fileObject = (JsonObject) data.get(i);
            System.out.println(fileObject.get("displayName"));
        }

//        System.out.println(data);

//        System.out.println(jsonObject.get("items"));



//        System.out.println(filesList);
    }

}
