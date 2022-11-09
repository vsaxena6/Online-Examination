import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class AUD extends HttpServlet
{
   String str1="";
   public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
   {
        res.setContentType("text/html");
        ServletOutputStream out=res.getOutputStream();
        str1=req.getParameter("user");
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                int i=st.executeUpdate("delete from musersinfo where loginid='"+str1+"'");
                out.println("<body background='http://peers:8080/paper_bg.gif'><font size=5px color=blue><center>");
                out.println("<br><br>Record Deleted Successfully");
                out.println("<a href='http://peers:8080/ausersd.html'>Back..</a>");
                out.println("</center></font></body>");
                st.close();
                c.close();
        }
        catch(Exception e)
        {
                out.println(e.toString());
        }
   }
}
