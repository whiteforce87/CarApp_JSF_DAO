package q1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import dao.systemdataDAO;
import model.SystemData;

public class Main {

	public static int situation;

	public static void main(String[] args) {

		ExecutorService srv = Executors.newCachedThreadPool();
		sqlThread sqlThread = new sqlThread();
		srv.submit(sqlThread);
		srv.shutdown();
	}

	static class sqlThread implements Runnable {
		List<SystemData> sysdata = new ArrayList<SystemData>();
		systemdataDAO status = new systemdataDAO();

		boolean isPaused = false;
		boolean isResumed = true;

		public sqlThread() {
			
		}

		@Override
		public void run() {

			while (true) {

				if (!isPaused) {
					sysdata = status.findAll();
					if (sysdata.get(0).getStatus() == 0) {
						situation = 0;
						System.out.println("System off");
						pauseThread();

					} else if (sysdata.get(0).getStatus() == 1) {
						situation = 1;
						System.out.println("System on");
						pauseThread();
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					try {
						synchronized (this) {
							wait(10);
							sysdata = status.findAll();
							chechsituation();

						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}

		private void chechsituation() {

			if ((situation == 0 && sysdata.get(0).getStatus() == 1)
					|| (situation == 1 && sysdata.get(0).getStatus() == 0)) {
				resumeThread();
			}

		}

		public void pauseThread() {
			isPaused = true;

		}

		public void resumeThread() {

			isPaused = false;

			synchronized (this) {
				notify();
			}
		}
	}
}
