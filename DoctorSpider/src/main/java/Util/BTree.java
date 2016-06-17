package Util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTree {
	public BTree(char o){
		setC(o);
		children=new LinkedList<BTree>();
	}
	public BTree(){
		children=new LinkedList<BTree>();
	}
	private char c;
	private List<BTree> children;

	public List<BTree> getChildren() {
		if(children==null) children=new LinkedList<BTree>();
		return children;
	}

	public void setChildren(List<BTree> children) {
		this.children = children;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
		List<Object> ll=new LinkedList<Object>();
	}
	public BTree GetChild(char c){
		for (BTree child : children) {
			if(child.getC()==c){
				return child;
			}
		}
		return null;
	}
	
	public void AddChild(Queue<Character> qc){
		if(qc.size()>0){
			char c=qc.remove();
			BTree b=null;
			if(GetChild(c)==null){
				b=new BTree(c);
				children.add(b);
			}else{
				b=GetChild(c);
			}
			b.AddChild(qc);
		}else{
			BTree b=new BTree('!');
			children.add(b);
		}
	}
	public void AddString(String s){
		Queue<Character> qs=CommonUtil.ConvertString(s);
		this.AddChild(qs);
	}
	public boolean Check(String s){
		Queue<Character> qc=CommonUtil.ConvertString(s);
		return this.Check(qc);
	}
	public boolean Check(Queue<Character> qc){
		if(qc.size()>0){
			char c=qc.remove();
			if(GetChild(c)!=null){
				return GetChild(c).Check(qc);
			}{
				return false;
			}
		}else{
			return GetChild('!')!=null;
		}
	}
	@Override
	public boolean equals(Object obj) {
		return this.c==(Character)obj;
	}
	
	public List<String> GetContents(){
		List<String> results=new LinkedList<String>();
		GetContents("", results);
		return results;
	}
	
	public void GetContents(String a,List<String> results){
		for(BTree t : children){
			if(t.getC()=='!'){
				results.add(a);
			}else{
				t.GetContents(a+t.getC(), results);
			}
		}
	}
}
