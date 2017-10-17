package ynu.edu.module.rule.BPMNtoPetri;

import ynu.edu.data.Graphics;
import ynu.edu.module.bpmn.BpmnElement;
import ynu.edu.module.petri.PetriElement;

public abstract class AbstractRule {
	
	public abstract boolean matches(Graphics<BpmnElement> graphics);
	
	protected abstract Graphics<PetriElement> split(Graphics<BpmnElement> graphics);
	
	public abstract Graphics<PetriElement> transfer(Graphics<BpmnElement> graphics);
	
}
