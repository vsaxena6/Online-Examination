import java.util.Random;
class RandDemo
{
        public static void main(String[] args)
        {
                Random r=new Random();
                double val;
                int bell[]=new int[25];
                int i,j,k,x;
                for(i=0;i<1000;i++)
                {
                        val=r.nextGaussian();
                        double t=-2;
                        for(x=0;x<25;x++,t+=0.18)
                        {
                                if(val < t)
                                {
                                        bell[x]++;
                                        break;
                                }
                        }
                 }
                 for(i=0;i<25;i++)
                 {
                     for(k=i;k<24;k++)
                     {
                        if(bell[i]==bell[k+1])
                        {
                                bell[k+1]=0;
                        }
                     }
                 }
                 k=0;
                 j=0;
                 while(k<20)
                 {
                       if(!(bell[j]==0))
                       {
                             System.out.print(k+1); 
                             for(i=0;i<bell[j];i++)
                             {
                                System.out.print("*");
                             }
                             System.out.println(bell[j]);
                             k=k+1;
                       }
                       j++;
                 }
        }
}
