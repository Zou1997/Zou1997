package Zou1997;

/**
  ѧ��������������ѧ������ģ�ѧ�������������洢��ѧ������Ϣ��
*/
public class Order{
	private long no;
	private String name;
	private int date;

	
	public Order(long no,String name,int date){
		this.no = no;
		this.name = name;
		this.date = date;
	}
	public void setNo(long no){
		this.no = no;
	}
	public long getNo(){
		return this.no;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setDate(int date){
		this.date = date;
	}
	public int getDate(){
		return this.date;
	}

	public String toString(){
		return "Student[no:"+this.no+",name:"+this.name+",date:"+this.date+"]";
	}
}