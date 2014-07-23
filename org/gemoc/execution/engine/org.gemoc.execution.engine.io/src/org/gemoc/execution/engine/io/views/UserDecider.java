package org.gemoc.execution.engine.io.views;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.gemoc.commons.eclipse.ui.ViewHelper;
import org.gemoc.execution.engine.io.SharedIcons;
import org.gemoc.execution.engine.io.views.event.EventManagerView;
import org.gemoc.execution.engine.io.views.step.LogicalStepsView;
import org.gemoc.gemoc_language_workbench.api.core.ILogicalStepDecider;

import fr.inria.aoste.trace.LogicalStep;

public class UserDecider implements ILogicalStepDecider {

	boolean isStepByStep = false;

	public UserDecider(boolean isStepByStep) {
		super();
		this.isStepByStep = isStepByStep;
	}

	private Semaphore _semaphore = null;

	@Override
	public int decide(final List<LogicalStep> possibleLogicalSteps)
			throws InterruptedException {
		_preemptionHappened = false;
		_semaphore = new Semaphore(0);
		if(!isStepByStep && possibleLogicalSteps.size() == 1) return 0;

		decisionView = ViewHelper.<LogicalStepsView>retrieveView(LogicalStepsView.ID);

		// add action into view menu
		IMenuListener2 menuListener = new IMenuListener2() 
		{

			private Action _action = null;

			public void menuAboutToShow(IMenuManager manager) 
			{
				if (_action == null
						&& decisionView.getSelectedLogicalStep() != null
						&& possibleLogicalSteps.contains(decisionView.getSelectedLogicalStep())) 
				{
					_action = createAction();
				}
				if (decisionView.getSelectedLogicalStep() != null
						&& _action != null)
					manager.add(_action);
			}

			public void menuAboutToHide(IMenuManager manager) 
			{
				if (_action != null)
					manager.remove(_action.getId());
			}
		};
		decisionView.addMenuListener(menuListener);

		// add action on double click
		IDoubleClickListener doubleClickListener = new IDoubleClickListener() 
		{
			public void doubleClick(DoubleClickEvent event) 
			{
				if (decisionView.getSelectedLogicalStep() != null
						&& possibleLogicalSteps.contains(decisionView.getSelectedLogicalStep())) 
				{
					Action selectLogicalStepAction = new Action() 
					{
						public void run() 
						{
							_semaphore.release();
						}
					};
					selectLogicalStepAction.run();
				}
			}
		};
		decisionView.addDoubleClickListener(doubleClickListener);

		// add action on double click
		ISelectionChangedListener selectionChangedListener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				EventManagerView eventView = ViewHelper.retrieveView(EventManagerView.ID);
				eventView.updateView();
			}
		};
		decisionView.addSelectionChangedListener(selectionChangedListener);


		// wait for user selection if it applies to this engine
		_semaphore.acquire();
		_semaphore = null;
		// clean menu listener
		decisionView.removeMenuListener(menuListener);
		decisionView.removeDoubleClickListener(doubleClickListener);
		decisionView.removeSelectionChangedListener(selectionChangedListener);
		if (_preemptionHappened)
			return -1;
		return possibleLogicalSteps.indexOf(decisionView.getSelectedLogicalStep());

	}

	private LogicalStepsView decisionView;

	@Override
	public void dispose() {
		if (_semaphore != null)
			_semaphore.release();
	}

	private boolean _preemptionHappened = false;
	@Override
	public void preempt() 
	{
		_preemptionHappened = true;
		if (_semaphore != null)
			_semaphore.release();
	}

	private Action createAction() {

		Action selectLogicalStepAction = new Action() 
		{
			public void run() 
			{
				_semaphore.release();
			}
		};
		selectLogicalStepAction.setId("org.gemoc.execution.engine.io.commands.SelectLogicalStep");
		selectLogicalStepAction.setText("Select LogicalStep");
		selectLogicalStepAction.setToolTipText("Use selected LogicalStep");
		selectLogicalStepAction.setImageDescriptor(SharedIcons.LOGICALSTEP_ICON);
		return selectLogicalStepAction;
	}
}
