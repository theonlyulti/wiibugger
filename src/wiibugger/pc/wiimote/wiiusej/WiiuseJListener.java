package wiibugger.pc.wiimote.wiiusej;
import wiibugger.pc.wiimote.WiimoteEventHandler;
import wiiusej.values.GForce;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;


public class WiiuseJListener implements WiimoteListener{
	
	private int leftOrRight;
	private boolean sendMotion;
	
	public WiiuseJListener(int wiimotePosition) {
		this.leftOrRight = wiimotePosition;
		
		/*
		 * Only send motion event every 100 milliseconds
		 */
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
						enableSendMotion();
					} catch (InterruptedException e) {}
				}
			}
		}.start();
	}
	
	synchronized private void enableSendMotion() {
		this.sendMotion = true;
	}
	
	synchronized private void disableSendMotion() {
		this.sendMotion = false;
	}
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) {
		
		if(event.isButtonAPressed())
			WiimoteEventHandler.buttonPressed(WiimoteEventHandler.A_BUTTON, leftOrRight);
		
		if(event.isButtonAJustReleased()) 
			WiimoteEventHandler.buttonReleased(WiimoteEventHandler.A_BUTTON, leftOrRight);

		if(event.isButtonBPressed())
			WiimoteEventHandler.buttonPressed(WiimoteEventHandler.B_BUTTON, leftOrRight);

		if(event.isButtonBJustReleased()) 
			WiimoteEventHandler.buttonReleased(WiimoteEventHandler.B_BUTTON, leftOrRight);
		
		if(event.isButtonDownPressed())
			WiimoteEventHandler.buttonPressed(WiimoteEventHandler.DOWN_BUTTON, leftOrRight);
		
		if(event.isButtonDownJustReleased())
			WiimoteEventHandler.buttonReleased(WiimoteEventHandler.DOWN_BUTTON, leftOrRight);
	}
	
	@Override
	public void onMotionSensingEvent(MotionSensingEvent event) {
		if (sendMotion) {
			disableSendMotion();
			GForce g = event.getGforce();
			WiimoteEventHandler.orientationEvent(g.getX(), g.getY(), g.getZ(), leftOrRight);
		}
	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0) { }

	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0) { }

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) { }

	@Override
	public void onExpansionEvent(ExpansionEvent arg0) { }

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) { }

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) { }

	@Override
	public void onIrEvent(IREvent arg0) { }

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) { }

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) { }

	@Override
	public void onStatusEvent(StatusEvent arg0) { }

}
