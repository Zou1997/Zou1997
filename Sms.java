package Zou1997;

import java.util.Scanner;
/**
	������Ϣ����ϵͳ
	��������Ϣ�ģ���ɾ�Ĳ飩
	����������Ϣ���浽���	��������
	���еĶ�����Ϣ���浽���	������
*/
public class Sms{
	private Order[] ords = new Order[3];//���ڱ��涩������
	private int index;	//��¼������ʵ�ʶ����ĸ���

	/**
	 * ��Ӷ�����Ϣ
	   save 
	     ords[0] = ord;  index = 1
		 ords[1] = ord;  index = 2
		 ords[2] = ord;	 index = 3
		 ords[3]
	 */
	public void save(Order order){
		if(index >= ords.length){
			//�������չ
			Order[] demo = new Order[ords.length+3];
			System.arraycopy(ords,0,demo,0,index);
			ords = demo;
		}
		ords[index++] = order;
	}

    /**
	 *�޸Ķ�����Ϣ
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
	 *ɾ��������Ϣ
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
	 *��ѯ���ж�����Ϣ
	 */
	public Order[] queryAll(){
		Order[] demo = new Order[index];
		System.arraycopy(ords,0,demo,0,index);
		return demo;
	}

	/**
	 *ͨ��id���Ҷ�����Ϣ

	 */
	public Order queryByNo(long no){
		int num = getIndexByNo(no);
		return num==-1?null:ords[num];
	}

	/**
	 ���ݶ��������id��ȡ�ö��������������е�����
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
	 * �˵�
  	 */
	public void menu(){
		System.out.println("********������Ϣ����ϵͳ********");
		System.out.println("*1 ��ѯ���ж�����Ϣ");
		System.out.println("*2 ¼�붩����Ϣ");
		System.out.println("*3 ɾ��������Ϣ");
		System.out.println("*4 ͨ����Ų��Ҷ�����Ϣ");
		System.out.println("*5 �޸Ķ�����Ϣ");
		System.out.println("*exit �˳�ϵͳ��");
		System.out.println("*help ��ȡ����");
		System.out.println("********************************");
	}

	public static void main(String[] args){
		Sms sms = new Sms();
		sms.menu();
		//ɨ��������
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("*�������Ӧָ��:");
			String option = sc.nextLine();
			switch(option){
				case "1":	//��ѯ����
					System.out.println("���������ж�������Ϣ��");
					Order[] ords = sms.queryAll();
					for(Order ord : ords){
						System.out.println(ord);
					}
					System.out.println("�ܹ���ѯ��"+sms.index+"������");
					break;
				case "2":	//¼��
					while(true){
						System.out.println("�����붩������Ϣ��no#name#date�������롾break��������һ��Ŀ¼");
						String ordStr = sc.nextLine();
						if(ordStr.equals("break")){
							break;//���ص���һ��Ŀ¼
						}
						//ordStr 1001#Zou#12�ַ��� ->����->����
						String[] ordArr = ordStr.split("#");
						//�������и�Ԫ��ת��Ϊ������������Ҫ����������
						long no  = Long.parseLong(ordArr[0]);
						String name = ordArr[1];
						int date = Integer.parseInt(ordArr[2]);
						//��װ����
						Order ord = new Order(no,name,date);
						sms.save(ord);
						System.out.println("����ɹ���");
					}
					break;
				case "3":	//ɾ��
					while(true){
						System.out.println("������Ҫɾ�������ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//��ѯҪɾ���Ķ�����Ϣ�Ƿ����
						Order ord = sms.queryByNo(no);
						if(ord==null){
							System.out.println("��Ҫɾ���Ķ�����Ϣ�����ڣ�");
							continue;
						}
						sms.deleteByNo(no);
						System.out.println("ɾ���ɹ���");
					}	
					break;
				case "4":	//ͨ��id��ȡ
					while(true){
						System.out.println("������Ҫ���Ҷ�����id,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						Order ord = sms.queryByNo(no);
						System.out.println(ord==null?"sorry,not found!":ord);
					}	
					break;
				case "5":	//�޸�
					while(true){
						System.out.println("������Ҫ�޸Ķ����ı��,��������break������һ��Ŀ¼");
						String noStr = sc.nextLine();
						if(noStr.equals("break")){
							break;
						}
						long no = Long.parseLong(noStr);
						//��ѯҪɾ���Ķ�����Ϣ�Ƿ����
						Order ord = sms.queryByNo(no);
						if(ord==null){
							System.out.println("��Ҫ�޸ĵĶ�����Ϣ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+ord);
						System.out.println("����������Ϣ��name#date����");
						// tom#12
						String str = sc.nextLine();
						String[] ordArr = str.split("#");
						String name = ordArr[0];
						int date = Integer.parseInt(ordArr[1]);
						Order newOrd = new Order(no,name,date);
						sms.update(newOrd);
						System.out.println("�޸ĳɹ���");
					}	
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					sms.menu();
					break;
				default:
					System.out.println("�������");

			}
		}
	}
}