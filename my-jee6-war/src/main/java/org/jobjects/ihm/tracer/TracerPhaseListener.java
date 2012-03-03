package org.jobjects.ihm.tracer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang3.ObjectUtils;

public class TracerPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		StringBuffer sb =new StringBuffer();
		sb.append("Source=");
		sb.append(ObjectUtils.toString(phaseEvent.getSource()));
		sb.append(" PhaseId=");
		sb.append(ObjectUtils.toString(phaseEvent.getPhaseId()));
		Logger.getLogger(getClass().getName()).log(Level.INFO, sb.toString());
	}

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		StringBuffer sb =new StringBuffer();
		sb.append("Source=");
		sb.append(ObjectUtils.toString(phaseEvent.getSource()));
		sb.append(" PhaseId=");
		sb.append(ObjectUtils.toString(phaseEvent.getPhaseId()));
		Logger.getLogger(getClass().getName()).log(Level.INFO, sb.toString());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
