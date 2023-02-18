package modelo.servicio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exceptions.InstanceNotFoundException;
import modelo.Empleado;
import util.SessionFactoryUtil;

public class EmpleadoServicio implements IEmpleadoServicio {

	@Override
	public Empleado findEmpById(int empId) throws InstanceNotFoundException {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Empleado empleado = session.get(Empleado.class, empId);
		if (empleado == null) {
			throw new InstanceNotFoundException(Empleado.class.getName());
		}

		session.close();
		return empleado;
	}

}
