package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ExclusiveGateway;
import ynu.edu.module.bpmn.ParallelGateway;
import ynu.edu.module.bpmn.SequenceFlow;

public class TransformRulefour1 extends AbstractRule{
	Graphics g;
	Hashtable<String,LinkedList<String>> h = g.getIds();
	String [] allID;
	String [][] IDbyNode ;
	
	public  TransformRulefour1(Graphics g) {
		// TODO Auto-generated constructor stub
		this.g = g;
	}
	
	
	
	
	//=================================================================================================//
	
	
	
	
	@Override
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		allID = h.get("parallelGateway").toArray(new String[h.get("parallelGateway").size()]);
		for (int i = 0 ; i < allID.length; i++)
		{
			IDbyNode = g.getIDbyNode(allID[i]);
			g.removeNode(allID[i]);
			ParallelGateway p = new ParallelGateway("one"+i);//new一个并行网关
			ParallelGateway p1 = new ParallelGateway("two"+i);//new一个并行网关
			
			if(IDbyNode[0].length<2 || IDbyNode[0].length<2)
			{
				continue;
			}
			for (int j = 0; j < IDbyNode[1].length; j++){
				g.addLink(IDbyNode[1][j],p.getId()) ;
			}
			for (int j = 0; j < IDbyNode[0].length; j++){
				g.addLink(p1.getId(), IDbyNode[0][j]);
			}
			SequenceFlow sequenceFlow =new SequenceFlow ("sfp"+i,p.getId(),p1.getId());
			//g.addLink(p.getId(), p1.getId());
		}
		
		
		allID = h.get("exclusiveGateway").toArray(new String[h.get("xclusiveGateway").size()]);
		for (int i = 0 ; i < allID.length; i++)
		{
			IDbyNode = g.getIDbyNode(allID[i]);
			g.removeNode(allID[i]);
			ExclusiveGateway p = new ExclusiveGateway(i+"one");//new一个并行网关
			ExclusiveGateway p1 = new ExclusiveGateway(i+"two");//new一个并行网关
			for (int j = 0; j < IDbyNode[1].length; j++){
				g.addLink(IDbyNode[1][j],p.getId()) ;
			}
			for (int j = 0; j < IDbyNode[0].length; j++){
				g.addLink(p1.getId(), IDbyNode[0][j]);
			}
			SequenceFlow sequenceFlow =new SequenceFlow ("sfe"+i,p.getId(),p1.getId());
			g.addNode(sequenceFlow);
			g.addNode(p);
			g.addNode(p1);
			
		}	
		
		
		return null;
	}

	
	@Override
	public boolean matches(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return false;
		
	}

	
	@Override
	public Graphics<BpmnElement> transfer(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		return null;
	}

}
