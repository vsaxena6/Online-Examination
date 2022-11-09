import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class Qadd extends HttpServlet
{
   public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
   {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String lang=req.getParameter("lan");
        String type=req.getParameter("type");
        int num=Integer.parseInt(req.getParameter("number"));
        String question=req.getParameter("quest");
        String ch1=req.getParameter("ch1");
        String ch2=req.getParameter("ch2");
        String ch3=req.getParameter("ch3");
        String ch4=req.getParameter("ch4");
        String ch5=req.getParameter("ch5");
        String ans=req.getParameter("answer");
        try
        {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection c=DriverManager.getConnection("jdbc:odbc:online");
                Statement st=c.createStatement();
                int n=st.executeUpdate("insert into mquestions values('" + lang + "','" + type +
                                "'," + num + ",'" + question + "','" + ch1 + "','" + ch2 +
                                "','" + ch3 + "','" + ch4 + "','" + ch5 + "','" + ans + "')");

                out.println("<html><body background='http://peers:8080/paper_bg.gif'><font size=4 color=blue><b><center>");
                out.println("<br><br>Record inserted Successfully");
                out.println("<a href='http://peers:8080/atquestions.html'>Back..</a>");
                out.println("</center></b></font></body></html>");
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
