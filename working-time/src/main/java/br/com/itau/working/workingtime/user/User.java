package br.com.itau.working.workingtime.user;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.itau.working.workingtime.time.Time;
import br.com.itau.working.workingtime.time.TimeHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Detalhes sobre o usuário")
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer userId;

	@Size(min = 6, message = "O nome deverá ter ao menos 6 caracteres")
	@ApiModelProperty(notes = "O nome deverá ter ao menos 6 caracteres")
	private String name;

	@Size(min = 9, message = "O CPF deverá ter ao menos 9 caracteres")
	@ApiModelProperty(notes = "O CPF deverá ter ao menos 9 caracteres")
	private String cpf;

	@Email(message = "Informe um email válido")
	@ApiModelProperty(notes = "Informe um email válido")
	private String email;

	@CreationTimestamp
	@Column(updatable = false)
	private Date created;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	@JsonInclude(Include.NON_EMPTY)
	private Set<Time> workTimeList;

	public User() {
	}

	public User(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Set<Time> getWorkTimeList() {
		return workTimeList;
	}

	public void setWorkTimeList(Set<Time> workTimeList) {
		this.workTimeList = workTimeList;
	}

	@JsonProperty(value = "workTimeByDay")
	@JsonInclude(value = Include.NON_EMPTY)
	public Map<String, LocalTime> getWorkTimeInMillis() {
		return TimeHelper.calculateWorkTimeByDay(workTimeList);
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, cpf=%s, email=%s, created=%s]", userId, name, cpf, email, created);
	}
}