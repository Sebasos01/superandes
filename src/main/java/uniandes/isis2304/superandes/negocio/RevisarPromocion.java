package uniandes.isis2304.superandes.negocio;

public class RevisarPromocion extends Thread {
	static long mlsFinalizarProm = 60000;
	
	private void finalizarPromocion() {}
	
	@Override
	public void run() {
		try {
			Thread.sleep(mlsFinalizarProm);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finalizarPromocion();
	}

}
