/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaic.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import qap.user.MultiUserJsf;

/**
 *
 * @author siete
 * 
 * ref: download file servlet spring boot
 * https://www.devglan.com/spring-boot/spring-boot-file-upload-download
 * https://www.javainuse.com/spring/boot-file-download
 * https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
 * https://o7planning.org/11765/spring-boot-file-download
 * 
 * 
 * https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
 * 
 * https://www.baeldung.com/register-servlet 
 * https://www.baeldung.com/upload-file-servlet
 * https://stackoverflow.com/questions/55978959/how-can-i-register-a-servlet-with-spring-boot
 * https://www.codejava.net/java-ee/servlet/java-servlet-download-file-example
 * 
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/file/*"})
public class FileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // Constants ----------------------------------------------------------------------------------
    public static final String FILE_RELATIVE_PATH = "/Fuentes/";

    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

    // Properties ---------------------------------------------------------------------------------

    //@Value("${path.image}") no anda, hay que obtenerlo de alguna clase de servicios svc
    private String filePath;

    // Actions ------------------------------------------------------------------------------------

    @Override
    public void init() throws ServletException {

        // Define base path somehow. You can define it as init-param of the servlet.
        this.filePath = FILE_RELATIVE_PATH;
        //this.filePath = ResourceBundle.getBundle("qap/config/bundles/app").getString("ArchivePath"); 
        //this.filePath = MultiUserJsf.getConfigProp("path.image");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // Get requested file by path info.
        String requestedFile = request.getPathInfo();

        // Check if file is actually supplied to the request URI.
        if (requestedFile == null) {
            // Do your thing if the file is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning page, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Decode the file name (might contain spaces and on) and prepare file object.
        File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));

        // Check if file actually exists in filesystem.
        if (!file.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning page, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Get content type by filename.
        String contentType = getServletContext().getMimeType(file.getName());

        // If content type is unknown, then set the default value.
        if (contentType == null) {
            contentType = "application/pdf";
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }
    }

    // Helpers (can be refactored to public utility class) ----------------------------------------

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
}
