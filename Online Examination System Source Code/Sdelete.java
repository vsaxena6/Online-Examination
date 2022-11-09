import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class Sdelete extends HttpServlet
{
   public void doGet(HttpServletRequest req,HttpServletResponse res)
   throws ServletException,IOException
   {
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        String code;
        code=req.getParameter("code");
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                int i=st.executeUpdate("delete from msubjectinfo where sub_code='"+ code +"'");
                int j=st.executeUpdate("delete from mquestions where lang='" + code +"'");
                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<font size=4 color=blue><b><center>");
                out.println("<br><br>");
                out.println("The subject code " + code +"is Deleted..");
                out.println("<br>");
                out.println(j + " Records are Deleted from Questions Database.");
                out.println("<br><a href='http://peers:8080/atsubjects.html'>Back..</a>");
                out.println("</center></b></font></body>");
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
