import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.net.*;
public class ASubja extends HttpServlet
{
     public void doGet(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException
     {

          PrintWriter out=res.getWriter();
          res.setContentType("text/html");
          out.println("<html><head>");
          out.println("<script>");
          out.println("function f1()");
          out.println("{");
          out.println("window.location.href='http://peers:8080/atsubjects.html';");
          out.println("}");
          out.println("function f3(fr)");
          out.println("{");
          out.println("if(fr.v1.value==''){");
          out.println("alert('Please enter The Subject Code')");
          out.println("fr.v1.focus()");
          out.println("}");
          out.println("if(fr.v1.value.length>1){");
          out.println("alert('Sorry! U Have to enter a Single character only.')");
          out.println("fr.v1.focus()");
          out.println("}");
          out.println("}");
          out.println("function f4(fr)");
          out.println("{");
          out.println("if(fr.v2.value==''){");
          out.println("alert('Please enter the Subject Title')");
          out.println("fr.v2.focus()");
          out.println("}");
          out.println("if(fr.v2.value.length>25){");
          out.println("alert(\"Sorry! The entered data is out of RANGE\")");
          out.println("fr.v2.focus()");
          out.println("}");
          out.println("}");
          out.println("</script></head>");
          out.println("<body background='http://peers:8080/paper_bg.gif'>");
          out.println("<form name=f2 method=post action='http://peers:8080/servlet/Sadd'>");
          out.println("<font size=4 color=blue><b><center>");
          out.println("Enter Subject Code    <input type=text name=v1 size=25 onChange=\"f3(this.form)\">");
          out.println("<br>Enter Subject Title   <input type=text name=v2 size=25 onChange=\"f4(this.form)\"><br><br>");
          out.println("<input type=submit value=Add> <input type=button value=back name=b1 onClick=\"f1()\">");
          out.println("</center></b></font></form></body></html>");
     }
     public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException
     {
        doGet(req,res);
     }

}
