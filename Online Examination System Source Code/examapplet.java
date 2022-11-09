/*<applet code=examapplet height=400 width=400>
</applet>*/


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.net.*;
public class examapplet extends Applet implements ActionListener,Runnable,ItemListener
{
Thread t=null;

        Button start=null,next=null,prev=null,submit=null,ok=null,logout=null;
        CheckboxGroup cbg=null;
        Checkbox cha=null,chb=null,chc=null,chd=null,che=null;
        Checkbox choa=null,chob=null,choc=null,chod=null,choe=null;

        Hashtable ques=null,ans=null;
        TextArea quest=null;
        int count=0;
        Panel textpanel=null,radio=null,check=null,buttons=null;
        Enumeration keys=null;
        Object key=null;
        String keyval=null;
        Label qno=null,status=null;
        String selectlang="";
        Choice langc=null;
        Label request=null;
public void init()
{
        setBackground(Color.lightGray);
        ans=new Hashtable();
        setLayout(null);

        qno=new Label();
        qno.setBounds(10,10,140,15);
        add(qno);

        status=new Label("Time:  00:00:00");
        status.setBounds(250,10,400,15);
        add(status);

        quest=new TextArea(5,60);
        quest.setEditable(false);
        quest.setBounds(10,40,480,140);
        add(quest);
        quest.setVisible(false);

        start=new Button("Start");
        start.setBounds(240,240,40,20);
        add(start);
        start.addActionListener(this);
        next=new Button("Next >>");
        next.addActionListener(this);
        prev=new Button("<< prev");
        prev.addActionListener(this);
        
        ok=new Button("Answer");
        ok.addActionListener(this);
        buttons=new Panel();
        buttons.setLayout(new GridLayout(1,3));
        buttons.add(prev);
        buttons.add(next);
        buttons.add(ok);
        buttons.setBounds(240,240,240,30);
        add(buttons);
        buttons.setVisible(false);

        submit=new Button("Submit");
        submit.setBounds(20,240,50,30);        
        add(submit);
        submit.setVisible(false);
        submit.addActionListener(this);

        cbg=new CheckboxGroup();
        cha=new Checkbox("A",cbg,true);
        chb=new Checkbox("B",cbg,false);
        chc=new Checkbox("C",cbg,false);
        chd=new Checkbox("D",cbg,false);
        che=new Checkbox("E",cbg,false);
        radio=new Panel();
        radio.setLayout(new GridLayout(1,5));
        radio.add(cha);
        radio.add(chb);
        radio.add(chc);
        radio.add(chd);
        radio.add(che);
        radio.setBounds(40,200,420,30);
        add(radio);
        radio.setVisible(false);

        choa=new Checkbox("A");
        chob=new Checkbox("B");
        choc=new Checkbox("C");
        chod=new Checkbox("D");
        choe=new Checkbox("E");
        check=new Panel();
        check.add(choa);
        check.add(chob);
        check.add(choc);
        check.add(chod);
        check.add(choe);
        check.setBounds(40,200,420,30);
        add(check);
        check.setVisible(false);

        request=new Label("Please select your subject from the choice list given under.");
        request.setBounds(20,110,460,20);
        add(request);
        t=new Thread(this,"timer");
        try
        {
                URL url1=new URL(getDocumentBase(),"/servlet/getLang");
                URLConnection urc1=url1.openConnection();
                ObjectInputStream ois1=new ObjectInputStream(urc1.getInputStream());
                String lang=(String)ois1.readObject();
                StringTokenizer st=new StringTokenizer(lang,"@");
                langc=new Choice();
                while(st.hasMoreTokens())
                {
                        langc.addItem(st.nextToken());
                }                
                ois1.close();
                langc.addItemListener(this);
                langc.setBounds(200,120,100,30);
                add(langc);         
         }
         catch(Exception e)
         {
         }
       
}
public void run()
{
int hours=0,minutes=0,seconds=0;
while(minutes<15)
{
  status.setText("Time:  "  + hours + ":" + minutes + ":" + seconds);
  try
  {
  Thread.sleep(1000);
  }
  catch(Exception e)
  {
  }
  seconds++;
  if(seconds==60)
  {
        minutes++;
        seconds=0;
  }
  if(minutes==60)
  {
        hours++;
        minutes=0;
  }
}
submit();
}
public void itemStateChanged(ItemEvent ie)
{
        selectlang=langc.getSelectedItem();
        status.setText(selectlang);
}
public void submit()
{
        try
        {
                URL url=new URL(getDocumentBase(),"/servlet/evaluate");
                URLConnection con=url.openConnection();
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestProperty("Content-Type","java-internal/" + ans.getClass().getName());

                OutputStream os=con.getOutputStream();
                ObjectOutputStream oos=new ObjectOutputStream(os);
                oos.writeObject(ans);
                oos.flush();
                oos.close();

                ObjectInputStream ois=new ObjectInputStream(con.getInputStream());
                String sresult=(String)ois.readObject();
                int marks=Integer.parseInt(sresult);

                submit.setVisible(false);     radio.setVisible(false);
                check.setVisible(false);      buttons.setVisible(false);
                quest.setVisible(false);      status.setVisible(false);
                qno.setVisible(false);
                submit=null;  radio=null;  check=null;  buttons=null;
                quest=null;  status=null;  qno=null;

                Label result,resgra,maxgra,max,marksl;
                result=new Label("You got " + (marks*100)/ans.size() + "% of Marks.");
                result.setBounds(20,60,440,20);
                add(result);
                max=new Label("Max Marks:  " );
                max.setBounds(20,100,100,20);
                add(max);
                marksl=new Label("Marks obtained:  " );
                marksl.setBounds(20,140,100,20);
                add(marksl);
                maxgra=new Label("");
                maxgra.setBounds(140,100,200,20);
                maxgra.setBackground(Color.blue);
                add(maxgra);
                resgra=new Label("");
                resgra.setBounds(140,140,((marks*200)/ans.size()),20);
                resgra.setBackground(Color.blue);
                add(resgra);

                logout=new Button("Logout");
                logout.addActionListener(this);
                logout.setBounds(230,200,40,30);
                add(logout);
        }
        catch(Exception e)
        {
        }
}       

public void actionPerformed(ActionEvent ae)
{
        if(ae.getSource()==logout)
        {
                AppletContext ac=getAppletContext();
                try
                {
                ac.showDocument(new URL(getDocumentBase(),"/logout.html"));
                }
                catch(Exception e)
                {
                }
        }
        if(ae.getSource()==start)
        {
        try
        {                                  
                URL url=new URL(getDocumentBase(),"/servlet/getquest?lang=" + selectlang);
                URLConnection con=url.openConnection();
                
                ObjectInputStream ois=new ObjectInputStream(con.getInputStream());
                ques=(Hashtable)ois.readObject();
                buttons.setVisible(true);
                start.setVisible(false);
                start=null;
                langc.setVisible(false);
                langc=null;
                request.setVisible(false);
                request=null;
                quest.setVisible(true);                
                quest.append("Navigate through the questions with the \nhelp of the buttons");
                t.start();
                Enumeration e=ques.keys();
                while(e.hasMoreElements())
                {
                        ans.put((String)e.nextElement(),"");
                }
        }
        catch(Exception e)
        {
        }
        }
        if(ae.getSource()==submit)
        {
		submit();
        }
        if(ae.getSource()==next)
        {
                choa.setState(false);cha.setState(false);
                chob.setState(false);chb.setState(false);
                choc.setState(false);chc.setState(false);
                chod.setState(false);chd.setState(false);
                choe.setState(false);che.setState(false);
                quest.setText("");                                
                keys=ques.keys();
                count++;
                int i=0;

                while(i<count)
                {
                if(count>ques.size())
                {               
                        count=ques.size();
                }
                if(count == ques.size())
                {
                        submit.setVisible(true);
                }
                else
                {
                        submit.setVisible(false);                        
                }
                if(keys.hasMoreElements())
                {
                        key=keys.nextElement();
                }
                i++;
                }
                Object obj=ques.get(key);
                keyval=(String)key;
                String str=(String)obj;
                StringTokenizer st=new StringTokenizer(str,"@");
                String str1=null;
                char ch='a';
                while(st.hasMoreElements())
                {
                        str1=st.nextToken();
                        if(!str1.equals("null"))
                        {
                        quest.append( str1 + "\n\n" + ch + ")  ");
                        che.setVisible(true);
                        ch++;
                        }
                        else
                        {
                                che.setVisible(false);
                        }
                }
                if(keyval.charAt(1)=='S' || keyval.charAt(1)=='s')
                {
                        radio.setVisible(true);
                        check.setVisible(false);                        
                }
                else
                {
                        radio.setVisible(false);
                        check.setVisible(true);
                }
                qno.setText("Question " + count + " of " + ques.size());
        }
        if(ae.getSource()==prev)
        {
                quest.setText("");
                choa.setState(false);cha.setState(false);
                chob.setState(false);chb.setState(false);
                choc.setState(false);chc.setState(false);
                chod.setState(false);chd.setState(false);
                choe.setState(false);che.setState(false);                

                keys=ques.keys();
                if(count <=1)
                {
                        count=1;
                        submit.setVisible(true);
                }
                else
                {
                        count--;
                        submit.setVisible(false);
                }
                int i=0;
                while(i<count)
                {
                if(keys.hasMoreElements())
                {
                        key=keys.nextElement();
                }
                i++;
                }
                Object obj=ques.get(key);
                keyval=(String)key;
                String str=(String)obj;
                StringTokenizer st=new StringTokenizer(str,"@");
                String str1=null;
                char ch='a';
                while(st.hasMoreElements())
                {
                        str1=st.nextToken();
                        if(!str1.equals("null"))
                        {
                        quest.append( str1 + "\n\n" + ch + ")  " );
                        che.setVisible(true);
                        ch++;
                        }
                        else
                        {
                                che.setVisible(false);
                        }                        
                }
                if(keyval.charAt(1)=='S' || keyval.charAt(1)=='s')
                {
                        radio.setVisible(true);
                        check.setVisible(false);                        
                }
                else
                {
                        radio.setVisible(false);
                        check.setVisible(true);
                }
                qno.setText("Question " + count + " of " + ques.size());        
        }
        if(ae.getSource()==ok)
        {
                String ans1="";

                if(keyval.charAt(1)=='S' || keyval.charAt(1)=='s')
                {                               
                        Checkbox cb=cbg.getSelectedCheckbox();
                        if(cb==cha) ans1="a";
                        if(cb==chb) ans1="b";
                        if(cb==chc) ans1="c";
                        if(cb==chd) ans1="d";
                        if(cb==che) ans1="e";
                        
                }
                else
                {                               
                        if(choa.getState()) ans1+="a";
                        if(chob.getState()) ans1+="b";
                        if(choc.getState()) ans1+="c";
                        if(chod.getState()) ans1+="d";
                        if(choe.getState()) ans1+="e";
                }
                status.setText(ans1);
                ans.put(keyval,ans1);                
        }
}
}
