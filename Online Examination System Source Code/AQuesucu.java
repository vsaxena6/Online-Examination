import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class AQuesucu extends HttpServlet
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
                String questno=req.getParameter("pcode");
                char lan=questno.charAt(0);
                char type=questno.charAt(1);
                int val=Integer.parseInt(questno.substring(2));
                ResultSet rs=st.executeQuery("select * from mquestions where lang='"
                                             + lan + "' and questype='" + type + "' and quesno=" + val);
                out.println("<html><head>");
                out.println("<script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href=\"http://peers:8080/servlet/AQuesu\";");
                out.println("}");
                out.println("</script></head>");
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<form action='http://peers:8080/servlet/AQuesucuc'>");
                out.println("<font size=4 color=blue><b>");
                rs.next();
                out.println("<center>Question no: <input type=text name=lang value='" + lan + type + val + "'>");
                out.println("<br><center>Question is</center> <br><center><textarea name=quest cols=40 rows=5>"+ rs.getString(4)+"</textarea></center>");
                out.println("<br><center>Choice1  is</center>  <br><center><textarea name=ch1 cols=40 rows=2>"+ rs.getString(5)+"</textarea></center>");
                out.println("<br><center>Choice2  is</center>  <br><center><textarea name=ch2 cols=40 rows=2>"+ rs.getString(6)+"</textarea></center>");
                out.println("<br><center>Choice3  is</center>  <br><center><textarea name=ch3 cols=40 rows=2>"+ rs.getString(7)+"</textarea></center>");
                out.println("<br><center>Choice4  is</center>  <br><center><textarea name=ch4 cols=40 rows=2>"+ rs.getString(8)+"</textarea></center>");
                out.println("<br><center>Choice5  is</center>  <br><center><textarea name=ch5 cols=40 rows=2>"+ rs.getString(9)+"</textarea></center>");
                out.println("<br>Answer is  <input type=text name=ans value='" + rs.getString(10) + "'>");
                rs.close();
                st.close();
                c.close();
                out.println("</b></font>");
                out.println("<center><input type=submit value=Submit name=b1>");
                out.println("<input type=button value=back onClick='f1()'>");
                out.println("</form></body></html>");
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
