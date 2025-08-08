package io.altar.jseproject.textinterface.states;

import java.util.Scanner;
import utils.ScannerUtils;

public abstract class State {
	
	public static final ScannerUtils sc = new ScannerUtils();

	public abstract int on();
}
