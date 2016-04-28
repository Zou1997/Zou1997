package Zou1997;

import java.util.Scanner;
/**
	订单信息管理系统
	管理订单信息的（增删改查）
	单个订单信息保存到哪里？	订单对象
	所有的订单信息保存到哪里？	数组中
*/
public class Sms{
	private Order[] ords = new Order[3];//用于保存订单对象
	private int index;	//记录数组中实际订单的个数

	/**
	 * 添加定单信息
	   save 
	     ords[0] = ord;  index = 1
		 ords[1] = ord;  index = 2
		 ords[2] = ord;	 index = 3
		 ords[3]
	 */
	public void save(Order order){
		if(index >= ords.length){
			//数组的扩展
			Order[] demo = new Order[ords.length+3];
			System.arraycopy(ords,0,demo,0,index);
			ords = demo;
		}
		ords[index++] = order;
	}

    /**
	 *修改订单信息
	 ords = {
		1001 Zou,
		1003 zou3,
		1004 zou4,
		1005 zou5,
		1006 zou6,
		null
	 }
		1006 Zou
	 */
	public void update(Order order){
		for(int i=0;i<index;i++){
			if(order.getNo() == ords[i].getNo()){
				ords[i].setName(order.getName());
			}
		}
	}

	/**
	 *删除订单信息
	 ords = new Order[6];
	 ords = {
		1001 Zou,
		1003 zou3,
		1004 zou4,
		1005 zou5,
		1006 zou6,
		null
	 }
	 index = 6;
	 1002    num = 1;
	 for(int i=1;i<5;i++){
		ords[i] = ords[i+1]
		//ords[4] = ords[5]

	 }
	 */
    public void deleteByNo(long no){
		int num = getIndexByNo(no);
		for(int i=num ;i<index-1;i++){
			ords[i] = ords[i+1];
		}
		ords[--index] = null;
	}

	/**
	 *查询所有订单信息
	 */
	public Order[] queryAll(){
		Order[] demo = new Order[index];
		System.arraycopy(ords,0,demo,0,index);
		return demo;
	}

	/**
	 *通过id查找订单信息

	 */
	public Order queryByNo(long no){
		int num = getIndexByNo(no);
		return num==-1?null:ords[num];
	}

	/**
	 根据订单对象的id获取该订单对象在数组中的索引
	 ords = new Order[3];
	 1001 Zou
	 1002 You
	 null 

	 1003
	*/
	private int getIndexByNo(long no){
		int num = -1;
		for(int i=0;i<index;i++){
			if(ords[i].getNo() == no){
				num = i;
				break;
			}
		}
		return num;
	}

	/**
	 * 菜单
  	 */
	public void menu(){
		System.out.println("********订单信息管理系统********");
		System.out.println("*1 查询所有订单信息");
		System.out.println("*2 录入订单信息");
		System.out.println("*3 删除订单信息");
		System.out.println("*4 通过编号查找订单信息");
		System.out.println("*5 修改订单信息");
		System.out.println("*exit 退出系统！");
		System.out.println("*help 获取帮助");
		System.out.println("********************************");
	}

	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		//扫描器对象
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("*请输入对应指令:");
			String option = sc.nextLine();
			switch(option){
				case "1":	//查询所有
					System.out.println("以下是所有订单的信息：");
					Order[] ords = sms.queryAll();
					for(Order ord : ords){
						System.out.println(ord);
					}
					System.out.println("总共查询到"+sms.index+"个订单");
					break;
				case "2":	//录入
					while(true){
						System.out.println("请输入订单的信息【no#name#date】或输入【break】返回上一级目录");
						String ordStr = sc.nextLine();
						if(ordStr.equals("break")){
							break;//返回到上一级目录
						}
						//ordStr 1001#Zou#12字符串 ->对象->数组
						String[] ordArr = ordStr.split("#");
						//将数组中个元素转换为订单属性所需要的数据类型
						long no  = Long.parseLong(ordArr[0]);
						String name = ordArr[1];
						int date = Integer.parseInt(ordArr[2]);
						//封装对象
						Order ord = new Order(no,name,date);
						sms.save(ord);
						System.out.println("保存成功！");
					}
					break;
				case "3":	//删除
					while(true){
						System.out.println("请输入要删除订单的编号,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//查询要删除的订单信息是否存在
						Order ord = sms.queryByNo(no);
						if(ord==null){
							System.out.println("您要删除的订单信息不存在！");
							continue;
						}
						sms.deleteByNo(no);
						System.out.println("删除成功！");
					}	
					break;
				case "4":	//通过id获取
					while(true){
						System.out.println("请输入要查找订单的id,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						Order ord = sms.queryByNo(no);
						System.out.println(ord==null?"sorry,not found!":ord);
					}	
					break;
				case "5":	//修改
					while(true){
						System.out.println("请输入要修改订单的编号,或者输入break返回上一级目录");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//查询要删除的订单信息是否存在
						Order ord = sms.queryByNo(no);
						if(ord==null){
							System.out.println("您要修改的订单信息不存在！");
							continue;
						}
						System.out.println("原信息为："+ord);
						System.out.println("请输入新信息【name#date】：");
						// tom#12
						String str = sc.nextLine();
						String[] ordArr = str.split("#");
						String name = ordArr[0];
						int date = Integer.parseInt(ordArr[1]);
						Order newOrd = new Order(no,name,date);
						sms.update(newOrd);
						System.out.println("修改成功！");
					}	
					break;
				case "exit":
					System.out.println("bye bye,欢迎再次使用！");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("输入错误！");

			}
		}
	}
}