/*import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class AQuesdc extends HttpServlet
{
     public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
     {
          res.setContentType("text/html");
          ServletOutputStream out=res.getOutputStream();
          try
          {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:eureka","eureka","eureka");
                Statement st=c.createStatement();
                String mcode=req.getParameter("code");

                HttpSession hs=req.getSession(true);
                hs.putValue("msrcode",mcode);

                ResultSet rs=st.executeQuery("select * from gquestions where qcode='"+mcode+"' ");
                out.println("<head>");
                out.println("<script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href=\"http://peers:8080/servlet/AQuesd\";");
                out.println("}");
                out.println("</script></head>");
                out.println("<body bgcolor=lightyellow background=paper_bg.gif><form method=post action=\"http://peers:8080/servlet/AQuesucd.class\">");
                out.println("<font size=5px color=blue><center>");
                out.println("Choose Question Id <select name=pcode size=1>");
                String msr;
                while(rs.next())
                {
                        msr=rs.getString(1);
                        out.println("<option>"+msr+"</option>");
                }
                out.println("</select><br><br></font>");
                out.println("<br><input type=submit value=Next>  <input type=button value=Back name=b1 onClick=\"f1()\">");
                out.println("</center></form></body>");
                rs.close();
                st.close();
                c.close();
        }
        catch(Exception e)
        {
                out.println(e.toString());
        }
     }
}
*/
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class AQuesdc extends HttpServlet
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
                String mcode=req.getParameter("code");


                ResultSet rs=st.executeQuery("select * from mquestions where lang='"+mcode+"' ");
                out.println("<html><head>");
                out.println("<script>");
                out.println("function f1()");
                out.println("{");
                out.println("window.location.href=\"http://peers:8080/servlet/AQuesd\";");
                out.println("}");
                out.println("</script></head>");
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<form method=post action='http://peers:8080/servlet/AQuesucd'>");
                out.println("<font size=4 color=blue><b><center>");
                out.println("Choose Question Id <select name=pcode>");
                String val="",lan="",type="";
                while(rs.next())
                {
                        val=rs.getString(3);
                        type=rs.getString(2);
                        lan=rs.getString(1);
                        out.println("<option value='"+ (lan + type + val) + "'>" + lan + type + val);
                }
                out.println("</select><br><br></b></font>");
                out.println("<br><input type=submit value=Next>  <input type=button value=Back name=b1 onClick='f1()'>");
                out.println("</center></form></body></html>");
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

