/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
/**
 */
package base.States;

import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specific Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see base.States.StatesPackage#getSpecificTracedObject()
 * @model abstract="true"
 * @generated
 */
public interface SpecificTracedObject<DimensionSubType extends SpecificDimension<? extends SpecificValue>> extends TracedObject<DimensionSubType> {
} // SpecificTracedObject
