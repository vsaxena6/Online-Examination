import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class ExSubjects extends HttpServlet
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
                ResultSet rs=st.executeQuery("select * from msubjectinfo");
                out.println("<html><body bgcolor=lightyellow background=paper_bg.gif><form>");
                out.println("<br><br><br>");
                out.println("<font size=5px color=red>");
                out.println("<Choose the subject>");
                out.println("<select name=subject size=1>");
                while(rs.next())
                {
                        String str=rs.getString(2);
                        out.println("<option>"+str+"</option>");
                }
                out.println("</select></font>");
                out.println("<b><a href=http://localhost/servlet/AQuestions>next..</a></b>");
                out.println("</form></body></html>");
        }
        catch(Exception e)
        {
                out.println(e.toString());
        }
   }
}
