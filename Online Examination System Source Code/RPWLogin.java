import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class RPWLogin extends HttpServlet
{     
     public void doGet(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException
     {
      
          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          boolean flag=false;
          try
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection c=DriverManager.getConnection("jdbc:odbc:online");
               Statement st=c.createStatement();
               ResultSet rs=st.executeQuery("select loginid,password from musersinfo");
               String user=req.getParameter("ui");
               String passwd=req.getParameter("pw");
               String s3="";
               String s4="";
               while(rs.next())
               {
                        s3=rs.getString(1);
                        s4=rs.getString(2);
                        if(s3.equals(user) && s4.equals(passwd))
                        {
                                res.sendRedirect("http://peers:8080/instr.html");
                                flag=true;
                        }
               }
               if(flag==false)
               {
                        out.println("<html><body background='http://peers:8080/paper_bg.gif'>");
                        out.println("<a href=http://peers:8080/regusers.html>");
                        out.println("<font size=4 color=red>");
                        out.println("<blink>Sorry U are an un authorised USER</blink>");
                        out.println("</font>");
                        out.println("</a>");
                        out.println("</body></html>");
               }
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
