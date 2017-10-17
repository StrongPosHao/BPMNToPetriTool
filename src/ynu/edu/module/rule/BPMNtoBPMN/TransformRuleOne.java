package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.GNode;
import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;
import ynu.edu.module.bpmn.StartEvent;

public class TransformRuleOne extends AbstractRule{
	public TransformRuleOne(Graphics g){
		this.g = g;
	}
	
	Graphics g;
	String startId;//开始事件的ID
	String one[] = g.getIDbyNode(startId)[0];//存储开始事件之后结点的ID
	ParallelGateway p = new ParallelGateway("one");//new一个并行网关
	Hashtable<String,LinkedList<String>> h = g.getIds();
	//SequenceFlow sequenceFlow = new SequenceFlow("");
	
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		
		if(one.length > 1)
		{
			startId = h.get("StartEvent").getFirst();//To Do
			g.removeNode(startId);
			g.addNode(p);
			
		}
		return null;
	}
	
	StartEvent start = new StartEvent("startId", p.getId());//新增一个开始事件
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		for (int i = 0; i < one.length; i++){
			if (g.addLink(p.getId(), one[i]) == false){
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
