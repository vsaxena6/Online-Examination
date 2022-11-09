import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class AUsersd extends HttpServlet
{
   String str1;
   public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
   {
        res.setContentType("text/html");
        ServletOutputStream out=res.getOutputStream();
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                ResultSet rs=st.executeQuery("select loginid from musersinfo");
                out.println("<body background=http://peers:8080/paper_bg.gif text=blue >");
                out.println("<br><br><br>");
                out.println("<font size=5px>");
                out.println("Choose the USERID");
                out.println("<form><select name=user size=1>");
                while(rs.next())
                {
                        String str=rs.getString(1);
                        out.println("<option>"+str+"</option>");
                } 
                out.println("</select></form>");
                out.println("<a href=http://peers:8080/servlet/AUD>Next..</a>  <a href=http://peers:8080/ausersd.html>Back..</a>");
                out.println("</body>");
                rs.close();
                st.close();
                c.close();
        }
        catch(Exception e)
        {
                out.println(e.toString());
        }
   }
}
