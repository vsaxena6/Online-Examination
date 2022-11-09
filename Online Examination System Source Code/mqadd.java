
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;

//public class Qadd extends HttpServlet
public class mqadd extends HttpServlet
{

    public void init(ServletConfig servletconfig)
        throws ServletException
    {
        super.init(servletconfig);
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection connection = DriverManager.getConnection("jdbc:odbc:online");
            st = connection.createStatement();
            return;
        }
        catch(Exception exception)
        {
            System.out.println("connection  :" + exception);
        }
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        httpservletresponse.setContentType("text/html");
        ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        String s = "";
        int i = 0;
        boolean flag = false;
     //   Cookie c1[]=httpservletrequest.getCookies();
     //   String sq=c1[0].getValue();
        try
        {
            rs = st.executeQuery("select name from sub");
            servletoutputstream.println("<form method=post action=\"http://peers:8080/servlet/Qadd0\">");
            servletoutputstream.println("<pre><font color=blue>");
            servletoutputstream.println("select subject :<select name=s>");
            String s1;
            for(; rs.next(); servletoutputstream.println("<option>" + s1))
                s1 = rs.getString(1);

            servletoutputstream.println("</select>");
            rs = st.executeQuery("select max(qno) from que");
            rs.next();
            int j = rs.getInt(1);
            Vector vector = new Vector(1, 1);
            int k = 0;
            int l = 0;
            for(rs = st.executeQuery("select qno from que"); rs.next(); vector.addElement(new Integer(k)))
                k = rs.getInt(1);

            vector.elements();
            k = 0;
            boolean flag1 = true;
            while(++l < j) 
                if(!vector.contains(new Integer(l)))
                {
                    i = l;
                    flag1 = false;
                    break;
                }

            rs = st.executeQuery("select max(qno) from que");
            if(!rs.next())
                i = 1;
            else
            if(flag1)
                i = rs.getInt(1) + 1;
            servletoutputstream.println("     Q.No :" + i + " <input type=hidden name=no size=20 value=" + i + ">");
            servletoutputstream.println("Question  :<input type=text name=que size=30>");
            servletoutputstream.println("     A    :<input type=text name=a size=30>");
            servletoutputstream.println("     B    :<input type=text name=b size=30>");
            servletoutputstream.println("     c    :<input type=text name=c size=30>");
            servletoutputstream.println("     D    :<input type=text name=d size=30>");
            servletoutputstream.println("Answer    :<input type=text name=ans size=5>");
            servletoutputstream.println("    <input type=submit name=send value=Add>");
            servletoutputstream.println("</font></pre></form>");
            rs.close();
            st.close();
            return;
        }
        catch(Exception exception)
        {
            servletoutputstream.println("sorry   :" + exception);
        }
    }

    //public Qadd()
	public mqadd()
    {
    }

    Connection c;
    Statement st;
    ResultSet rs;
}
