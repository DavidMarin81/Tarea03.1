package modelo.servicio;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exceptions.InstanceNotFoundException;
import modelo.Account;
import modelo.Departamento;
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

	@Override
	public List<Account> getAllEmpAccounts(int id) {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Account> cuentas = session.createQuery("select d from Account d WHERE emp.empno = " + id).list();
		session.close();

		return cuentas;
	}

}
