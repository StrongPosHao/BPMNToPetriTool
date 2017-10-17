package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.EndEvent;
import ynu.edu.module.bpmn.ExclusiveGateway;

public class TransformRuleTwo extends AbstractRule{
	
	public TransformRuleTwo(Graphics g){
		this.g = g;
	}
	
	
	Graphics g;
	String endId;//结束事件的ID
	String two[] = g.getIDbyNode(endId)[1];//存储开始事件之后结点的ID
	ExclusiveGateway e = new ExclusiveGateway("two");//new一个并行网关
	Hashtable<String,LinkedList<String>> h = g.getIds();

	@Override
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		if(two.length > 2)
		{
			endId = h.get("EndEvent").getFirst();//To Do
			g.removeNode(endId);
			g.addNode(e);
		}
		return null;
	}
	EndEvent end = new EndEvent("endId", e.getId());//新增一个结束事件的结点
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		for (int i = 0; i < two.length; i++){
			if (g.addLink(two[i], e.getId()) == false){
				return false;
			}
		}
		return true;
	}

	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
