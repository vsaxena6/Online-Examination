import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class ASubju extends HttpServlet
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
                out.println("<html><head><script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href='http://peers:8080/atsubjects.html';");
                out.println("}");
                out.println("function f3()");
                out.println("{");
                out.println("alert('Modifications...');");
                out.println("}");
                out.println("</script></head>");
                String msr;
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<form name=f2 method=post action=\"http://peers:8080/servlet/Supdate\">");
                out.println("<font size=4 color=blue>");
                out.println("Choose Subject Code <select name=code onChange='f3()'>");
                while(rs.next())
                {
                        msr=rs.getString(1);
                        out.println("<option value='" + msr + "'>" + msr);
                }
                out.println("</select>");
                out.println("<br>Subject Title       <input type=text name=title size=15><br><br>");
                out.println("</b></font><font color=blue size=4>");
                out.println("<br><input type=submit value=Update> <input type=button value=Back name=b1 onClick='f1()'>");
                out.println("</pre></font></form></body>");
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
