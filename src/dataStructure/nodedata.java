package dataStructure;

import utils.Point3D;

import java.io.Serializable;

import dataStructure.node_data;

public class nodedata implements node_data,Serializable {
	
	private int key;
	private Point3D location;
	private double weight;
	private int tag;
	private String info;
	private String way;
	
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public nodedata() {
		this.key=0;
		this.location=null;
		this.weight=0;
		this.tag=0;
		way=null;
	}
	public nodedata(int key,Point3D location, double weight,int tag) {
		this.key=key;
		this.location=location;
		this.weight=weight;
		this.tag=tag;
		this.way=null;
	}
	public nodedata(nodedata other) {
		this.key=other.key;
		this.location=other.location;
		this.weight=other.weight;
		this.tag=other.tag;
		way=other.way;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub
		this.location=new Point3D(p.x(),p.y(),p.z());
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.weight=w;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		//String data="key="+this.key+"location="+this.location+"weight="+this.weight+"tag="+this.tag;
		return "";
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info=s;
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
