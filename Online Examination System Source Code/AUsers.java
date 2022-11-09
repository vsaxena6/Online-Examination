import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class AUsers extends HttpServlet
{
     String s1,s2;
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          ServletOutputStream out=res.getOutputStream();
          try
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection c=DriverManager.getConnection("jdbc:odbc:online");
               Statement st=c.createStatement();
               s1=req.getParameter("aui");
               s2=req.getParameter("apw");
               int i=st.executeUpdate("insert into madmininfo values('"+s1+"','"+s2+"')");
               out.println("<html><body background='http://peers:8080/paper_bg.gif' text=blue><font size=5px>");
               out.println("New Admin-id   "+s1+"  is Created");
               out.println("<br><a href=\"http://peers:8080/aus.html\">Back..</a>");
               out.println("</font></body></html>");
               st.close();
               c.close();
          }
          catch(Exception e)
          {
               out.println(e.toString());
          }
     }
}
