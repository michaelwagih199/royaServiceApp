package com.omelnur.roya.royaServiceApp.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "name not null")
	@Column(length = 20)
	private String name;

	@Temporal(TIMESTAMP)
	protected Date createdDate;

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "permission_roles",
			joinColumns = { @JoinColumn(name = "ROLE_ID") },
			inverseJoinColumns = { @JoinColumn(name = "permission_ID") })
	private Set<Permission> permissions;

}