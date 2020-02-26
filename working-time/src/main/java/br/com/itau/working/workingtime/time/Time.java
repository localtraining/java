package br.com.itau.working.workingtime.time;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Detalhes sobre o ponto eletrônico dos usuários")
@Entity
@Table(name = "work_time")
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeid")
	private Integer timeId;
	
	@Column(name = "userid")
	private Integer userId;

	@CreationTimestamp
	private LocalDateTime created;

	private TimeEventType type;

	protected Time() {
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public TimeEventType getType() {
		return type;
	}

	public void setType(TimeEventType type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
