package modelo.servicio;

import exceptions.InstanceNotFoundException;
import modelo.Empleado;

public interface IEmpleadoServicio {
	
	public Empleado findEmpById(int empId) throws InstanceNotFoundException;
}
