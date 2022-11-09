import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class Subjects extends HttpServlet
{

   public void doGet(HttpServletRequest req,HttpServletResponse res)
   throws ServletException,IOException
   {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                ResultSet rs=st.executeQuery("select * from msubjectinfo");
                out.println("<html><body background='http://peers:8080/paper_bg.gif'>");
                out.println("<br><br><br>");
                out.println("<font size=4 color=blue>");
                out.println("Choose the subject");
                out.println("<form><select name=subject>");
                while(rs.next())
                {
                        String subject=rs.getString(2);
                        out.println("<option value='"+ rs.getString(1) + "'>" + subject);
                } 
                out.println("</select></form>");
                out.println("<a href=http://peers:8080/servlet/QD>next..</a>");
                out.println("<a href=http://peers:8080/instr.html>back..</a>");
                out.println("</body></html>");
                rs.close();
                st.close();
                c.close();
        }
        catch(Exception e)
        {
                out.println(e.toString());
        }
   }
   public void doPost(HttpServletRequest req,HttpServletResponse res)
   throws ServletException,IOException
   {
        doGet(req,res);
   }

}
