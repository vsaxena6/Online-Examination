import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class ASubjl extends HttpServlet
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

                out.println("<html><head>");
                out.println("<script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href='http://peers:8080/atsubjects.html';");
                out.println("}");
                out.println("</script></head>");

                String code,name;
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<form>");
                out.println("<font size=4 color=red><i><center>");
                out.println("<table name='list' border=0 bgcolor=skyblue>");
                out.println("<tr><th>Subject code</th><th>Subject Title</th></tr>");
                while(rs.next())
                {
                        code=rs.getString(1);
                        name=rs.getString(2);
                        out.println("<tr><td>" + code + "</td><td>" + name + "</td></tr>");
                }
                out.println("</table></center>");
                out.println("</i></font><font color=blue size=4>");
                out.println("<br><center><input type=button value=Back name=b1 onClick='f1()'></center>");
                out.println("</font></form></body></html>");
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
