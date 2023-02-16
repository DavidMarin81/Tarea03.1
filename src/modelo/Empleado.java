package modelo;
// Generated 21:11:36, 10 de xan. de 2023 by Hibernate Tools 5.6.14.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Emp generated by hbm2java
 */
public class Empleado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer empno;
	private Departamento dept;
	private Empleado jefe;
	private String ename;
	private String job;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private Set<Account> accounts = new HashSet(0);
	private Set<Empleado> suborninado = new HashSet(0);

	public Empleado() {
	}

	public Empleado(Departamento dept, Empleado jefe, String ename, String job, Date hiredate, BigDecimal sal, BigDecimal comm,
			Set<Account> accounts, Set<Empleado> suborninado) {
		this.dept = dept;
		this.jefe = jefe;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.accounts = accounts;
		this.suborninado = suborninado;
	}

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Departamento getDept() {
		return this.dept;
	}

	public void setDept(Departamento dept) {
		this.dept = dept;
	}

	public Empleado getEmp() {
		return this.jefe;
	}

	public void setEmp(Empleado emp) {
		this.jefe = jefe;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public BigDecimal getSal() {
		return this.sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public BigDecimal getComm() {
		return this.comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Empleado> getEmps() {
		return this.suborninado;
	}

	public void setEmps(Set<Empleado> suborninado) {
		this.suborninado = suborninado;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + ", sal=" + sal
				+ ", comm=" + comm + "]";
	}
	
	

}
