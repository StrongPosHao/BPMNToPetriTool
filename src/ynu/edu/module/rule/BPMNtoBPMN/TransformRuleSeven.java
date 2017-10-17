package ynu.edu.module.rule.BPMNtoBPMN;

import java.util.Hashtable;
import java.util.LinkedList;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.bpmn.ParallelGateway;

public class TransformRuleSeven extends AbstractRule{
	
	Graphics g;
	Hashtable<String,LinkedList<String>> h = g.getIds();
	String [] allParallelGatewayID;
	String [] allExclusiveGatewayID;
	String [][] IDbyNode ;
	boolean flag1;
	boolean flag2;
	@Override
	protected Graphics<BpmnElement> split(Graphics<BpmnElement> graphics) {
		// TODO Auto-generated method stub
		
		allParallelGatewayID = h.get("parallelGateway").toArray(new String[h.get("parallelGateway").size()]);
		allExclusiveGatewayID = h.get("exclusiveGateway").toArray(new String[h.get("exclusiveGateway").size()]);
		
		
		for (int i = 0 ; i < allParallelGatewayID.length; i++)
		{
			
			IDbyNode = g.getIDbyNode(allParallelGatewayID[i]);
			for(int j = 0;i<allParallelGatewayID.length; j++)
			{
				if(g.getIDbyNode(IDbyNode[0][0])[0][0]==allParallelGatewayID[j])  //取当前网关的下一个的下一个元素是不是网关
					flag1=true;
			}
			for(int j = 0;i<allExclusiveGatewayID.length; j++)
			{
				if(g.getIDbyNode(IDbyNode[0][0])[0][0]==allExclusiveGatewayID[j])
					flag2=true;
			}
			if(flag1||flag2)
			{
				g.removeNode(IDbyNode[0][0]);
				
				
				
				
			}
			g.removeNode(allParallelGatewayID[i]);
			ParallelGateway p = new ParallelGateway("one");//new一个并行网关
			ParallelGateway p1 = new ParallelGateway("two");//new一个并行网关
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
			g.addLink(p.getId(), p1.getId());
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
