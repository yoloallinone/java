Index.html

<html>
    <form action="login">
        Name<input type="text" name="t1">
        Pass ord <input type="Password" name="t2">
        <input type="submit">
    </form>
</html>



login.java

import javax.servlet.*;
import java.io.*;

public class login extends GenericServlet
{
    public void service(ServletRequest req,ServletResponse res)throws IOException,ServletException
    {
       String n =req.getParameter("t1");
       String  p=req.getParameter("t2");
        PrintWriter pw=res.getWriter();
        if(n.equals ("admin") && p.equals("admin123"))
        {
            pw.print("Welcome "+n);            
        }
        else 
        {
                    pw.print("Login Failed");
        }

        pw.close();
    }
}
