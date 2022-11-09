import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class ASubjd extends HttpServlet
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

                String code;
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<form name=f2 method=post action='http://peers:8080/servlet/Sdelete'>");
                out.println("<font size=4 color=blue><center>");
                out.println("Choose Subject Code <select name=code>");
                while(rs.next())
                {
                        code=rs.getString(1);
                        out.println("<option value='" + code +"'>" + code);
                }
                out.println("</select><br><br></font><font color=blue size=4>");
                out.println("<br><input type=submit value=Delete>");
                out.println("<input type=button value=Back name=b1 onClick='f1()'>");
                out.println("</center></font>");
                out.println("<br><br><font size=4 color=red><b>");
                out.println("<center>Warning:  If U delete the Subject Code");
                out.println("then the corresponding RECORDS will also be DELETED.");
                out.println("<center></b></font></form></body></html>");
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
