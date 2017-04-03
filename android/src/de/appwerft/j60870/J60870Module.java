package de.appwerft.j60870;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;

@Kroll.module(name = "J60870", id = "de.appwerft.j60870")
public class J60870Module extends KrollModule {

	public J60870Module() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}

}
