package dataStructure;

import java.io.Serializable;

import utils.Point3D;

public class edgedata implements edge_data,Serializable{
	
	public nodedata getDes() {
		return des;
	}
	public void setDes(nodedata des) {
		this.des = des;
	}
	public void setSource(nodedata source) {
		this.source =  (source);
	}

	public nodedata getSource() {
		return source;
	}

	private nodedata source;
	private nodedata des;
	private double weight;
	private int tag;
	private String info;
	
	
	public edgedata () {
		 source=null;
		 des=null;
		 weight=0;
		 tag=0;
		 info="";
	}
	public edgedata (nodedata source, nodedata des,double weight,int tag) {
		this.source=(source);
		this.des= (des);
		this.weight=weight;
		this.tag=tag;
		this.info="";
	}
	public edgedata (edgedata other) {
		this.source=new nodedata(other.source);
		this.des=new nodedata(other.des);
		this.weight=other.weight;
		this.tag=other.tag;
		this.info=other.info;
	}


	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return this.source.getKey();
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return this.des.getKey();
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		//String data="source="+this.source+"des="+this.des+"weight="+this.weight+"tag="+this.tag;
		return info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		info=s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this.tag=t;
	}
	

}
