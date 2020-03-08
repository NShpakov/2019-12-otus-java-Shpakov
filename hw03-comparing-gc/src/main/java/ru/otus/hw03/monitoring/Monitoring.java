package ru.otus.hw03.monitoring;

import com.sun.management.GarbageCollectionNotificationInfo;
import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class Monitoring {

	private static GC youngMBean;
	private static GC oldMBean;
	private static GC othersMBean;

	public static void run() {
		register();
		switchOnMonitoring();
		Thread youngThread = new Thread(youngMBean);
		Thread oldThread = new Thread(oldMBean);
		Thread others = new Thread(othersMBean);
		youngThread.start();
		oldThread.start();
		others.start();
	}

	private static void switchOnMonitoring() {
		List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean gcbean : gcbeans) {
			System.out.println("GC name:" + gcbean.getName());
			NotificationEmitter emitter = (NotificationEmitter) gcbean;
			NotificationListener listener = (notification, handback) -> {
				if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
					GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
					String gcName = info.getGcName();

					System.out.println("notification.getSequenceNumber() = " + notification.getSequenceNumber() +
							" info.getGcName() = " + info.getGcName() +
							" info.getGcAction() = " + info.getGcAction() +
							" info.getGcCause() = " + info.getGcCause() +
							" notification.getMessage() = " + notification.getMessage()
					);

					if (gcName.toUpperCase().contains(GC_TYPES.YOUNG.name())) {
						youngMBean.setCount(youngMBean.getCount() + 1);
						youngMBean.setDuration(youngMBean.getDuration() + info.getGcInfo().getDuration());
					} else if (gcName.toUpperCase().contains(GC_TYPES.OLD.name())) {
						oldMBean.setCount(oldMBean.getCount() + 1);
						oldMBean.setDuration(oldMBean.getDuration() + info.getGcInfo().getDuration());
					} else {
						othersMBean.setCount(othersMBean.getCount() + 1);
						othersMBean.setDuration(othersMBean.getDuration() + info.getGcInfo().getDuration());
					}
				}
			};
			emitter.addNotificationListener(listener, null, null);
		}
	}

	private static void register() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName youngName = null;
		ObjectName oldName = null;
		ObjectName othersName = null;
		try {
			youngName = new ObjectName("otus.ru.otus.hw03.monitoring.YoungGC:type=GCMBean");
			oldName = new ObjectName("otus.ru.otus.hw03.monitoring.OldGC:type=GCMBean");
			othersName =  new ObjectName("otus.ru.otus.hw03.monitoring.OthersGC:type=GCMBean");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}

		youngMBean = new GC(GC_TYPES.YOUNG);
		oldMBean = new GC(GC_TYPES.OLD);
		othersMBean = new GC(GC_TYPES.OTHERS);

		try {
			mbs.registerMBean(youngMBean, youngName);
			mbs.registerMBean(oldMBean, oldName);
			mbs.registerMBean(othersMBean, othersName);
		} catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}
}