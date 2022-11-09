import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class QD extends HttpServlet
{
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          PrintWriter  out=res.getWriter();
          try
          {
               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
               Connection c=DriverManager.getConnection("jdbc:odbc:online");
               Statement st=c.createStatement();
               ResultSet rs1=st.executeQuery("select count(*) from mq");
               rs1.next();
               int k=rs1.getInt(1);
               int j=0;
               ResultSet rs=st.executeQuery("select qid,qname,ans1,ans2,ans3,ans4,qans,qflag from mq where qcode='J'");
               while(rs.next())
               {
                   j=j+1;
                   String temp;
                   temp=rs.getString(1);
                   int s1=Integer.parseInt(temp);
                   String s2=rs.getString(2);
                   String s3=rs.getString(3);
                   String s4=rs.getString(4);
                   String s5=rs.getString(5);
                   String s6=rs.getString(6);
                   String s7=rs.getString(7);
                   String s8=rs.getString(8);
                   if(s8.equals("T"))
                   {
                         out.println("<body bgcolor=lightyellow><form action=http://peers:8080/servlet/All>");
                         String msr="Q"+j+":";
                         out.println(msr+s2);
                         out.println("<br><input type=radio value=A name=r1 unchecked>"+s3+"");
                         out.println("<br><input type=radio value=B name=r1 unchecked>"+s4+"");
                         out.println("<br><input type=radio value=C name=r1 unchecked>"+s5+"");
                         out.println("<br><input type=radio value=D name=r1 unchecked>"+s6+"");
                         out.println("<br><br>                <input type=submit value=next>");
                         out.println("</form></body>");
                         int i=st.executeUpdate("update mq set qflag='F' where qid="+s1);
                         break;
                    }
               }
               if(k==j)
               {
                    String hh="T";
                    int jj=st.executeUpdate("update mq set qflag='"+hh+"'");
               }
          }
          catch(Exception e)
          {
               out.println("error is"+":"+e.toString());
          }
     }
}
