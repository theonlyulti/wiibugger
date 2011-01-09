package wiibugger.pc;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import wiibugger.pc.wiimote.WiimoteScanner;

public class ScanWiimoteAction extends AbstractAction implements Runnable {

	private static final long serialVersionUID = 1L;

	public ScanWiimoteAction() {
		super("Scan");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setEnabled(false);
		WiimoteScanner.scan(Wiibugger.getWiimoteList(), 2, this);
	}

	@Override
	public void run() {
		this.setEnabled(true);
	}

}