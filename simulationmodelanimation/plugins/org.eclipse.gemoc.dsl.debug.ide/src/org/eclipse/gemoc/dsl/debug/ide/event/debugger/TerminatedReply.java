/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.event.debugger;


/**
 * Reply sent when the debugger or a thread is terminated.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TerminatedReply extends AbstractThreadReply {

	/**
	 * Constructor for {@link org.eclipse.gemoc.dsl.debug.DebugTarget DebugTarget}.
	 */
	public TerminatedReply() {
		this(null);
	}

	/**
	 * Constructor for {@link org.eclipse.gemoc.dsl.debug.Thread Thread}.
	 * 
	 * @param threadName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Thread#getName() thread name}
	 */
	public TerminatedReply(String threadName) {
		super(threadName);
	}

}
