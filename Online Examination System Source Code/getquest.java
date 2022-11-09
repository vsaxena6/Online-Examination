import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class getquest extends HttpServlet
{
Hashtable ht=null;
Connection con=null;
Statement st=null;
ResultSet rs=null,rs1=null;
public void doGet(HttpServletRequest req,HttpServletResponse res)
throws IOException,ServletException
{
        String lang=req.getParameter("lang");
        /*try
        {
        ObjectInputStream ois=new ObjectInputStream(req.getInputStream());
        lang=(String)ois.readObject();
        }
        catch(Exception e)
        {
        }*/
        
        String queskey=null,quesvalue=null;
        ht=new Hashtable();
        String code="";
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:online");
        st=con.createStatement();
        rs1=st.executeQuery("select sub_code from msubjectinfo where sub_title='" + lang + "'");
        rs1.next();
        //code=rs1.getString(1);
        rs=st.executeQuery("select * from mquestions where lang='" + rs1.getString(1) + "'");        
        while(rs.next())
        {
                queskey=rs.getString(1) + rs.getString(2) + rs.getString(3);
                quesvalue=rs.getString(4) + "@" + rs.getString(5) + "@" +
                    rs.getString(6) + "@" + rs.getString(7) + "@" +
                    rs.getString(8) + "@" + rs.getString(9);
                ht.put(queskey,quesvalue);
        }
        }
        catch(Exception e)
        {
        }
        OutputStream os=res.getOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(os);
        oos.writeObject(ht);
}
}
