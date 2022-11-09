import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class getLang extends HttpServlet
{
Connection con=null;
Statement st=null;
ResultSet rs=null;
public void doGet(HttpServletRequest req,HttpServletResponse res)
throws IOException,ServletException
{
        try
        {           
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:online");
        st=con.createStatement();
        rs=st.executeQuery("select * from msubjectinfo");
        String lang="";
        while(rs.next())
        {
              lang+= rs.getString(2) + "@";
        }
        ObjectOutputStream oos=new ObjectOutputStream(res.getOutputStream());
        oos.writeObject(lang);
        }
        catch(Exception e)
        {
                
        }
}
}
