import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class Supdate extends HttpServlet
{
   public void doGet(HttpServletRequest req,HttpServletResponse res)
   throws ServletException,IOException
   { 
        PrintWriter out=res.getWriter();
        res.setContentType("text/html");
        String code,name;
        code=req.getParameter("code");
        name=req.getParameter("title");
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                int i=st.executeUpdate("update msubjectinfo set sub_title='"+ name +"' where sub_code='"+code+"' ");
                out.println("<html><body background='http://peers:8080/paper_bg.gif'><font size=4 color=blue><b>");
                out.println("Record Updated Sucessfully");
                out.println("<a href=\"http://peers:8080/atsubjects.html\">Back..</a></b></font></body></html>");
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
