//upload

index.html

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Practical 3C</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Refresh" content="0; URL=NonBlockingServlet"> 
    </head>
    <body>
     
    </body>
</html>

NonBlockingServlet.java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class NonBlockingServlet extends HttpServlet {
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {

 out.println("<h1>FileReader</h1>");
 String filename="/booklist.txt";
 ServletContext c=getServletContext();
 InputStream in=c.getResourceAsStream(filename);
 String
path="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/ReadingNonBloclingServlet";
 URL url=new URL(path);
 HttpURLConnection conn=(HttpURLConnection)url.openConnection();
 conn.setChunkedStreamingMode(2);
 conn.setDoOutput(true);
 conn.connect();
 if(in!=null)
 {
 InputStreamReader inr=new InputStreamReader(in);
 BufferedReader br = new BufferedReader(inr);
 String text="";
 System.out.println("Reading started....");
 BufferedWriter bw=new BufferedWriter(new
OutputStreamWriter(conn.getOutputStream()));
 while((text=br.readLine())!=null){
 out.print(text+"<br>");
 try{
 Thread.sleep(1000);
 out.flush();
 }
 catch(InterruptedException ex){}
}out.print("reading completed....");
 bw.flush();
 bw.close();
 }}}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


ReadingListener.java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
public class ReadingListener implements ReadListener
{
 private ServletInputStream input = null;
 private AsyncContext ac = null;
 ReadingListener(ServletInputStream in, AsyncContext c) {
 input = in;
 ac = c;
 }
 @Override
 public void onDataAvailable() throws IOException {
 }
 public void onAllDataRead() throws IOException {
 ac.complete();
 }
 public void onError(final Throwable t) {
 ac.complete();
 t.printStackTrace();
 }
}

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    








ReadingNonBlockingServlet.java

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
*
* @author Beena
*/
@WebServlet (name = "ReadingNonBlockingServlet", urlPatterns =
{"/ReadingNonBlockingServlet"},asyncSupported = true )
public class ReadingNonBlockingServlet extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html");
 AsyncContext ac = request.startAsync();
 ServletInputStream in=request.getInputStream();
 in.setReadListener(new ReadingListener(in,ac));
 }
//}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

///downlaod
//index.html
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
          <h1>File Download Application</h1>       
          Click <a href="DownloadServlet?filename=nightlife.txt">NL</a>    
          <br/><br/>    
          Click <a href="DownloadServlet?filename=TOC.pdf">Table Of Contents</a> 
    </body>
</html>

DownloadServlet.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author babu chodhari
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String fileToDownload = request.getParameter("filename");
        //this is the ?filename parameter
        System.err.println("Downloading file now...");
        downloadFile(request,response,fileToDownload);
    }
        private void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName ) throws ServletException, IOException{
        int length = 0;
        try(ServletOutputStream outputStream = response.getOutputStream())
        {
            ServletContext context = getServletConfig().getServletContext();
            response.setContentType((context.getMimeType(fileName) !=null) ?
                    context.getMimeType(fileName) : "application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\""+
                    fileName);
            InputStream inputStream = context.getResourceAsStream("/"+
                    fileName);
            byte[] bytes = new byte[1024];
            
            while((inputStream !=null) && ((length = inputStream.read(bytes)) != -1))
            {
                outputStream.write(bytes,0,length);
                
            }
            outputStream.flush();
        }


        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownloadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

