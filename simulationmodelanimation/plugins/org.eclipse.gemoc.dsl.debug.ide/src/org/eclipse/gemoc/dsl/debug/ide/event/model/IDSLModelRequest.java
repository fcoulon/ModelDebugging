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
package org.eclipse.gemoc.dsl.debug.ide.event.model;

import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent;

/**
 * An {@link IDSLDebugEvent debugger event} from the
 * {@link org.eclipse.gemoc.dsl.debug.ide.adapter.DSLDebugTargetAdapter debug target} to the
 * {@link org.eclipse.gemoc.dsl.debug.ide.IDSLDebugger debugger}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IDSLModelRequest extends IDSLDebugEvent {

}
