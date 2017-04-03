package de.appwerft.j60870;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.openmuc.j60870.IeAckFileOrSectionQualifier;
import org.openmuc.j60870.IeBinaryCounterReading;
import org.openmuc.j60870.IeCauseOfInitialization;

@Kroll.module(parentModule = J60870Module.class)
public class InformationElementModule extends KrollModule {

	public InformationElementModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}

	@Kroll.method
	public IeAckFileOrSectionQualifier createAFQ(int action, int notice) {
		return new IeAckFileOrSectionQualifier(action, notice);
	}

	@Kroll.method
	public IeBinaryCounterReading createBCR(int counterReading,
			int sequenceNumber, boolean carry, boolean counterAdjusted,
			boolean invalid) {
		return new IeBinaryCounterReading(counterReading, sequenceNumber,
				carry, counterAdjusted, invalid);
	}

	@Kroll.method
	public IeCauseOfInitialization createCOI(int value,
			boolean initAfterParameterChange) {
		return new IeCauseOfInitialization(value, initAfterParameterChange);
	}

}
