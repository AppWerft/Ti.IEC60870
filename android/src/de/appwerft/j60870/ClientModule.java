package de.appwerft.j60870;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.json.JSONArray;
import org.json.JSONException;
import org.openmuc.j60870.IeAckFileOrSectionQualifier;
import org.openmuc.j60870.IeBinaryCounterReading;
import org.openmuc.j60870.IeBinaryStateInformation;
import org.openmuc.j60870.IeCauseOfInitialization;
import org.openmuc.j60870.IeChecksum;
import org.openmuc.j60870.InformationElement;

@Kroll.module(parentModule = J60870Module.class)
public class ClientModule extends KrollModule {

	public ClientModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}

	@Kroll.method
	public void createElements(KrollDict elemsJSON) {
		try {
			JSONArray elems = new JSONArray(elemsJSON);
			List<InformationElement> infoelems = new ArrayList<InformationElement>();
			for (int i = 0; i < elems.length(); i++) {
				JSONArray elem = elems.getJSONArray(i);
				switch (elem.getString(0)) {
				case "AFQ":
					infoelems.add(new IeAckFileOrSectionQualifier(elem
							.getInt(1), elem.getInt(2)));
					break;
				case "BCR":
					infoelems.add(new IeBinaryCounterReading(elem.getInt(1),
							elem.getInt(2), elem.getBoolean(3), elem
									.getBoolean(4), elem.getBoolean(5)));
					break;
				case "BSI":
					infoelems.add(new IeBinaryStateInformation(elem.getInt(1)));
					break;
				case "COI":
					infoelems.add(new IeCauseOfInitialization(elem.getInt(1),
							elem.getBoolean(2)));
					break;
				case "CHS":
					infoelems.add(new IeChecksum(elem.getInt(1)));
					break;

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
