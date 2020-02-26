package br.com.itau.working.workingtime.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TimeHelper {
	private TimeHelper() {
	}
	
	public static Map<String, LocalTime> calculateWorkTimeByDay(Set<Time> workTimeList) {
		Map<String, LocalTime> workTimeMap = new HashMap<>();

		if(workTimeList != null) {
			Map<LocalDate, List<Time>> list = workTimeList.stream()
					.collect(Collectors.groupingBy(time -> time.getCreated().toLocalDate()));
	
			for (Map.Entry<LocalDate, List<Time>> pair : list.entrySet()) {
				List<Time> starting = pair.getValue().stream().filter(type -> type.getType() == TimeEventType.STARTING)
						.collect(Collectors.toList());
				List<Time> finishing = pair.getValue().stream().filter(type -> type.getType() == TimeEventType.FINISHING)
						.collect(Collectors.toList());
	
				if (starting.size() != finishing.size()) {
					workTimeMap.put(pair.getKey().toString(), null);
				} else {
					LocalTime temp = LocalTime.MIN;
	
					for (int f = 0; f < finishing.size(); f++) {
						LocalDateTime finishingTime = finishing.get(f).getCreated();
						LocalDateTime startingTime = starting.get(f).getCreated();
	
						temp = temp.plus(Duration.between(startingTime, finishingTime));
					}
	
					workTimeMap.put(pair.getKey().toString(), temp);
				}
			}
		}

		return workTimeMap;
	}
}
