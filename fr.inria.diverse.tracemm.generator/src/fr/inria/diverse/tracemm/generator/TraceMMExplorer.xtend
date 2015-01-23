package fr.inria.diverse.tracemm.generator

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtend.lib.annotations.Accessors
import java.util.Set

class TraceMMExplorer {

	EPackage tracemm

	// Base classes
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass globalStateClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass traceSystemClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventOccClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass eventsTracesClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass tracedObjectsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EClass staticObjectsPoolsClass
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage eventsPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage tracedPackage
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER) protected EPackage statesPackage

	private Set<EClass> eventClasses

	/**
	 * Here we focus on the part of the base trace mm, because TraceMMExplorer is
	 * used in the TraceMMGenerator as well.
	 */
	new(EPackage traceMetamodel) {
		this.tracemm = traceMetamodel

		// Find the TraceSystem class
		traceSystemClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_TraceSystem)] as EClass

		// Find the GlobalState class
		globalStateClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_GlobalState)] as EClass

		// Find the EventOcc class and EventsTraces class and Events package
		eventOccClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_EventOccurrence)] as EClass
		eventsTracesClass = tracemm.eAllContents.filter(EClass).findFirst[c|
			c.name.equals(TraceMMStringsCreator.class_EventsTraces)] as EClass
		eventsPackage = eventOccClass.EPackage

		// Find the TracedObjects class and Traced package
		tracedObjectsClass = tracemm.eAllContents.filter(EClass).findFirst[p|
			p.name.equals(TraceMMStringsCreator.class_TracedObjects)] as EClass
		tracedPackage = tracedObjectsClass.EPackage

		// Find the States package
		statesPackage = tracemm.eAllContents.filter(EPackage).findFirst[p|
			p.name.equals(TraceMMStringsCreator.package_States)] as EPackage

		// Find the StaticObjectsPools class
		staticObjectsPoolsClass = tracemm.eAllContents.filter(EClass).findFirst[p|
			p.name.equals(TraceMMStringsCreator.class_StaticObjectsPools)] as EClass

	}

	def Set<EClass> findEventClasses() {
		if (eventClasses == null) {
			eventClasses = eventsPackage.eAllContents.filter(EClass).filter[c|
				c != eventOccClass && c != eventsTracesClass].toSet
		}
		return eventClasses
	}
}


