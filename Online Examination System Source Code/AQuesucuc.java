import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.sql.*;
public class AQuesucuc extends HttpServlet
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

                String questno=req.getParameter("lang");
                char lan=questno.charAt(0);
                char type=questno.charAt(1);
                int val=Integer.parseInt(questno.substring(2));

                String quest=req.getParameter("quest");
                String ch1=req.getParameter("ch1");
                String ch2=req.getParameter("ch2");
                String ch3=req.getParameter("ch3");
                String ch4=req.getParameter("ch4");
                String ch5=req.getParameter("ch5");
                String ans=req.getParameter("ans");


                int i=st.executeUpdate("update mquestions set question='"+quest+"',choice1='"+ch1+"',choice2='"+ch2+
                "',choice3='"+ch3+"',choice4='"+ch4+"',choice5='"+ch5+"',answer='"+ans+"' where (lang='" + lan +
                "' and questype='" + type + "' and quesno=" + val + ")");

                out.println("<body background='http://peers:8080/paper_bg.gif'>");
                out.println("<font size=4 color=blue><b><center>");
                out.println("Record Updated Sucessfully");
                out.println("<a href='http://peers:8080/servlet/AQuesu'>");
                out.println("Back..</a></b></font></center></body></html>");
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
