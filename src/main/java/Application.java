import entities.Department;
import entities.Employee;
import entities.EmployeeAddress;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class Application {
    public static void main(String[] args) {


//        Employee employee = findById(1);
//        EmployeeAddress employeeAddress = EmployeeAddress.builder()
//                .address("Советская-Боконбаевна")
//                .employee(employee)
//                .build();
//
//        createOrUpdate(employeeAddress);
//
//         employee2 = findById(2);
//        EmployeeAddress employeeAddress2 = EmployeeAddress.builder()
//                .address("8 Микрорайон")
//                .employee(employee2)
//                .build();
//
//        createOrUpdate(employeeAddress2);
//
//         employee3 = Employee.builder().id(10).name("Фёдор").age(40).build();
//        create(employee3);
//        EmployeeAddress employeeAddress3 = EmployeeAddress.builder()
//                .address("Площадь")
//                .employee(employee3)
//                .build();
//
//        createOrUpdate(employeeAddress3);

        Department department1 = new Department();
        department1.setName("Backend");
        Department department2 = new Department();
        department2.setName("Frondend");
        Department department3 = new Department();
        department3.setName("Mobile dev");

        Employee employee1 = new Employee(1, "Erkin", 25,department1);
        Employee employee2 = new Employee(2,"Emir",35,department2);
        Employee employee3 = new Employee(3,"Ruslan",25,department3);
        Employee employee4 = new Employee(4,"Kuba",27,department1);

        createOrUpdate(department1);
        createOrUpdate(department2);
        createOrUpdate(department3);
        createOrUpdate(employee1);
        createOrUpdate(employee2);
        createOrUpdate(employee3);
        createOrUpdate(employee4);

        List<Employee> employeesResult = getAll();

    }

    public static <T> T createOrUpdate(T entity){
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(entity);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        return entity;
    }

    public static Integer create(Employee employee) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.save(employee);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Успешно создан " + employee.toString());
        return employee.getId();
    }

    public static List<Employee> getAll() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Employee> employees = hibernateSession.createQuery("FROM Employee").list();
        hibernateSession.close();
        System.out.println("Найдено " + employees.size() + " сотрудников");
        return employees;
    }

    public static void update(Employee employee) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Employee employeeFromBase = (Employee) hibernateSession.load(Employee.class, employee.getId());
        employeeFromBase.setName(employee.getName());
        employeeFromBase.setAge(employee.getAge());
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Успешно изменено " + employeeFromBase.toString());
    }

    public static Employee findById(Integer id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Employee employee = hibernateSession.load(Employee.class, id);
        hibernateSession.close();
        return employee;
    }

    public static void delete(Integer id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Employee employee = hibernateSession.load(Employee.class, id);
        hibernateSession.delete(employee);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Успешно удалено " + employee.toString());
    }

    public static void deleteAll(){
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Query hibernateQuery = hibernateSession.createQuery("DELETE FROM Employee ");
        hibernateQuery.executeUpdate();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Успешно удалены все записи в Employee");
    }


}
