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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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


         JSONObject jObject = new JSONObject(jString);
       JSONObject geoObject = jObject.getJSONObject("geodata");

       String geoId = geoObject.getString("id");
           System.out.println(geoId);

       String name = geoObject.getString("name");
       System.out.println(name);

       String gender=geoObject.getString("gender");
       System.out.println(gender);

       String lat=geoObject.getString("latitude");
       System.out.println(lat);

       String longit =geoObject.getString("longitude");
       System.out.println(longit);

        System.out.println(filesList);
    }

}
