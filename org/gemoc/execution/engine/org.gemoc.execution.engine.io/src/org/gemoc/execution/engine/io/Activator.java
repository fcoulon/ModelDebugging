package org.gemoc.execution.engine.io;

import java.util.HashMap;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gemoc.gemoc_language_workbench.api.core.GemocExecutionEngine;
import org.kermeta.utils.systemservices.eclipse.api.ConsoleLogLevel;
import org.kermeta.utils.systemservices.eclipse.api.EclipseMessagingSystem;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.gemoc.execution.engine.io"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	
	
	protected static EclipseMessagingSystem messagingSystem = null;

	public static EclipseMessagingSystem getMessagingSystem() {
		if (messagingSystem == null) {
			messagingSystem = new EclipseMessagingSystem(PLUGIN_ID,
					"GEMOC Execution Engine Input/Output");
			((EclipseMessagingSystem) messagingSystem)
					.setConsoleLogLevel(ConsoleLogLevel.DEV_DEBUG);
		}
		return messagingSystem;
	}
	
	
	

}
