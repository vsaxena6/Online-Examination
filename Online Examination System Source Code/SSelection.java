import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class SSelection extends HttpServlet
{
     String s1,s2;
     boolean flag=false;
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          ServletOutputStream out=res.getOutputStream();
          try
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection c=DriverManager.getConnection("jdbc:odbc:online");
               Statement st=c.createStatement();
               ResultSet rs=st.executeQuery("select loginid,password from musersinfo");
               s1=req.getParameter("ui");
               s2=req.getParameter("pw");
               String s3="",s4="";
               while(rs.next())
               {
                        s3=rs.getString(1);
                        s4=rs.getString(2);
                        if(s3.equals(s1) && s4.equals(s2))
                        {
                                flag=true;
                                res.sendRedirect("http://peers:8080/atopics.html");
                        }
               }
               if(flag=false)
               {
                        out.println("<body bgcolor=lightyellow text=blue>");
                        out.println("<a href=http://peers:8080/regusers.html>");
                        out.println("<font size=5px color=red>");
                        out.println("Sorry U are un authorised USER");
                        out.println("</font>");
                        out.println("</a>");
                        out.println("</body>");
               } 
          }
          catch(Exception e)
          {
               out.println(e.toString());
          }
     }
}
