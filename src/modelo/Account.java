package modelo;
// Generated 21:11:36, 10 de xan. de 2023 by Hibernate Tools 5.6.14.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Account generated by hbm2java
 */
public class Account implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer accountno;
	private Empleado emp;
	private BigDecimal amount;
	private Set<AccMovement> accMovementsDest = new HashSet<AccMovement>(0);
	private Set<AccMovement> accMovementsOrigen = new HashSet<AccMovement>(0);

	public Account() {
	}

	public Account(Empleado emp, BigDecimal amount) {
		this.emp = emp;
		this.amount = amount;
	}

	public Account(Empleado emp, BigDecimal amount, Set<AccMovement> accMovementsDest,Set<AccMovement> accMovementsOrigin ) {
		this.emp = emp;
		this.amount = amount;
		this.accMovementsDest = accMovementsDest;
		this.accMovementsOrigen = accMovementsOrigin;
	}

	public Integer getAccountno() {
		return this.accountno;
	}

	public void setAccountno(Integer accountno) {
		this.accountno = accountno;
	}

	public Empleado getEmp() {
		return this.emp;
	}

	public void setEmp(Empleado emp) {
		this.emp = emp;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Set<AccMovement> getAccMovementsDest() {
		return this.accMovementsDest;
	}



	public Set<AccMovement> getAccMovementsOrigen() {
		return accMovementsOrigen;
	}

	public void setAccMovementsOrigen(Set<AccMovement> accMovementsOrigen) {
		this.accMovementsOrigen = accMovementsOrigen;
	}

	public void setAccMovementsDest(Set<AccMovement> accMovementsDest) {
		this.accMovementsDest = accMovementsDest;
	}
	

}
