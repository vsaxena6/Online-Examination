import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
import java.util.Vector;
public class AQuesa extends HttpServlet
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
                out.println("<html><head>");
                out.println("<script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href='http://peers:8080/atquestions.html';");
                out.println("}");
                out.println("</script></head>");
                out.println("<body background='http://peers:8080/paper_bg.gif'><form method=post name=fr action='http://peers:8080/servlet/Qadd'>");
                out.println("<font size=4 color=blue><b>");
                ResultSet rs=st.executeQuery("select max(quesno) from mquestions");
                if(rs!=null)
               {
                rs.next();                
                }
                String str=rs.getString(1);
                int number=Integer.parseInt(str);
                ResultSet rs1=st.executeQuery("select * from msubjectinfo");

                out.println("<center>Question no :  <select name=lan>");
                while(rs1.next())
                {
                        out.println("<option value='" + rs1.getString(1) +"'>" + rs1.getString(2));
                }
                out.println("</select>");
                out.println("<select name=type><option value='s'>Single<option value='m'>Multiple</select>");
                out.println("<input type=text name=number size=4 value='" + (number+1) +"'><center><br>");
                out.println("Enter Question <br><center>     <textarea cols=40 name=quest rows=5></textarea></center>");
                out.println("Enter Choice 1 <br><center>      <textarea cols=40 name=ch1 rows=2></textarea></center>");
                out.println("Enter Choice 2       <br><center><textarea cols=40 name=ch2 rows=2></textarea></center>");
                out.println("Enter Choice 3       <br><center><textarea cols=40 name=ch3 rows=2></textarea></center>");
                out.println("Enter Choice 4       <br><center><textarea cols=40 name=ch4 rows=2></textarea></center>");
                out.println("Enter Choice 5       <br><center><textarea cols=40 name=ch5 rows=2></textarea></center>");

                out.println("Enter Answer ");   
                out.println("<input type=text name=answer size=5>");
                out.println("                                     <center>     <input type=submit value=Add name=b1> <input type=button value=back onClick=\"f1()\"></center>");
                out.println("</b></font></form></body></html>");
        }
        catch(Exception e)
        {                                                                               
                out.println(e.toString());
        }

     }
}
