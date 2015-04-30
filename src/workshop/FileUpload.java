/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop;

/**
 * @author bulivaa <arthur.buliva@unon.org>
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.HttpClient;

/**
 * @author aaa
 *
 */
public class FileUpload
{

    public static void uploadDocument(String authTicket, File fileobj,
            String filename, String filetype, String description,
            String destination)
    {
        try
        {

            String urlString = "http://10.104.104.27:8080/alfresco/service/api/upload?alf_ticket="
                    + authTicket;
            System.out.println("The upload url:::" + urlString);
            HttpClient client = new HttpClient();
            PostMethod mPost = new PostMethod(urlString);
// File f1 =fileobj;
            Part[] parts =
            {
                new FilePart("filedata", filename, fileobj, filetype, null),
                new StringPart("filename", filename),
                new StringPart("description", description),
                //new StringPart("destination", destination),
                new StringPart("description", description),
                //modify this according to where you wanna put your content
                new StringPart("siteid", "document-manager"),
                new StringPart("containerid", "documentLibrary"),
//                new StringPart("uploaddirectory", "/CompanyHome")
            };
            mPost.setRequestEntity(new MultipartRequestEntity(parts, mPost
                    .getParams()));
            int statusCode1 = client.executeMethod(mPost);
//            System.out.println(mPost.getResponseBodyAsString());


            System.out.println(mPost.getResponseBodyAsString());

            
            mPost.releaseConnection();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String args[]) throws IOException
    {
        String username = "arthur";
        String password = "Lemonade";

        String alfrescoTiccketURL = "http://10.104.104.27:8080/alfresco"
                + "/service/api/login?u=" + username + "&pw=" + password;
        InteractiveAuthentication ticket = new InteractiveAuthentication();
        String ticketURLResponse = ticket.getTicket(alfrescoTiccketURL);


        File f = new File("/home/bulivaa/Documents/Resume and Cover Letter/ArthurBuliva.pdf");
        FileInputStream is = new FileInputStream(f);
        uploadDocument(ticketURLResponse,
                f, f.getName(), "pdf file",
                "application/pdf",
                "workspace://SpacesStore/document-manager");
    }

}
