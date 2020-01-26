package ru.otus.hw03;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComparingGcMain {
	private static final int TIME = 60000;
	private static final long TIME_START = System.currentTimeMillis();
	private static int countListElements;

	public static void main(String[] args) throws InterruptedException {

	//	createObjects(10000000);
calcInteger();


	}
//
	private static void createObjects(int countListElements) {
		List objects = new ArrayList(countListElements);
		System.out.println("Creating objects...");
		for (int i = 0; i < countListElements; i++) {
			objects.add(new byte[2]);
			objects.remove(i);
			System.out.println("Размер списка :" + objects.size());
			//objects.add(new byte[1]);
			//objects = null;
			System.out.println("метод1 "+i);
			long l2 = System.currentTimeMillis();
			long finishTime = TimeUnit.SECONDS.toSeconds(l2 - TIME_START);
			if (finishTime == TIME) {
				System.out.println("Программа отработала:" + TIME / 1000 + "секунд");
				break;

			}

		}
	}

	private static void calcInteger() throws InterruptedException {
		System.out.println( "Starting pid: " + ManagementFactory.getRuntimeMXBean().getName() );
		switchOnMonitoring();
		long timestart = System.currentTimeMillis();
		final int limit = Integer.MAX_VALUE;
		Integer counter = 0;
		for ( int idx = 0; idx < limit; idx++ ) {
			long l2 = System.currentTimeMillis();
			long finishTime = TimeUnit.SECONDS.toSeconds(l2 - TIME_START);
			counter += Integer.valueOf( 1 );
			System.out.println(counter);

			if (finishTime == TIME) {
				System.out.println("Программа отработала:" + TIME / 1000 + "секунд");
				break;

			}

		//	if ( idx % 1_000_000 == 0 ) {
		//		Thread.sleep( 1000 );
		//	}
		}

		long finishTime = System.currentTimeMillis();
		long res = finishTime - timestart;
		System.out.println( counter );
		System.out.println(res /1000);
	}
	private static void switchOnMonitoring() {
		List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
		for ( GarbageCollectorMXBean gcbean : gcbeans ) {
			System.out.println( "GC name:" + gcbean.getName() );
			NotificationEmitter emitter = (NotificationEmitter) gcbean;
			NotificationListener listener = (notification, handback ) -> {
				if ( notification.getType().equals( GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION ) ) {
					GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from( (CompositeData) notification.getUserData() );
					String gcName = info.getGcName();
					String gcAction = info.getGcAction();
					String gcCause = info.getGcCause();

					long startTime = info.getGcInfo().getStartTime();
					long duration = info.getGcInfo().getDuration();

					System.out.println( "start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)" );
				}
			};
			emitter.addNotificationListener( listener, null, null );
		}
	}

}
