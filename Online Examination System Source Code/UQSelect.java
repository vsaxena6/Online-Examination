import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Random;
import java.net.*;
public class UQSelect extends HttpServlet
{
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          ServletOutputStream out=res.getOutputStream();
          Random r=new Random();
          double val;
          int bell[]=new int[20];
          try
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection c=DriverManager.getConnection("jdbc:odbc:online");
               Statement st=c.createStatement();
               ResultSet rs=st.executeQuery("select * from msrao");
               for(int i=0;i<1000;i++)
               {
                       val=r.nextGaussian();
                       double t=-2;
                       for(int x=0;x<20;x++,t+=0.18)
                       {
                               if(val < t)
                               {
                                       bell[x]++;
                                       break;
                               }
                      }
               }
               for(int j=0;j<20;j++)
               { 
               }
          }
          catch(Exception e)
          {
               out.println(e.toString());
          }
     }
}

