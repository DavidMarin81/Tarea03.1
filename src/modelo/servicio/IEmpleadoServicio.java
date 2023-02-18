package modelo.servicio;

import java.util.List;

import exceptions.InstanceNotFoundException;
import modelo.Account;
import modelo.Empleado;

public interface IEmpleadoServicio {
	
	public Empleado findEmpById(int empId) throws InstanceNotFoundException;
	public List<Account> getAllEmpAccounts(int id);
}
