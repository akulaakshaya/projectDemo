package eStoreProduct.DAO.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eStoreProduct.model.customer.entities.custCredModel;

@Component
public class customerDAOImp implements customerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(custCredModel ccm) {
		entityManager.persist(ccm);
	}

	@Override
	@Transactional
	public boolean createCustomer(custCredModel ccm) {
		try {
			// Get the highest cust_id from slam_customers
			String maxIdQuery = "SELECT MAX(c.custId) FROM custCredModel c";
			Integer maxId = entityManager.createQuery(maxIdQuery, Integer.class).getSingleResult();

			// Increment the cust_id for the new customer
			int newCustId = (maxId != null) ? maxId + 1 : 1;
			ccm.setCustId(newCustId);

			// Persist the new customer
			entityManager.persist(ccm);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public void updatelastlogin(int cid) {
		String updateQuery = "UPDATE custCredModel SET custLastLoginDate = CURRENT_TIMESTAMP WHERE custId = :cid";
		entityManager.createQuery(updateQuery).setParameter("cid", cid).executeUpdate();
	}

	@Override
	@Transactional
	public void updateccm(custCredModel ccm) {
		entityManager.merge(ccm);
	}

	@Override
	@Transactional
	public custCredModel getCustomerById(int id) {
		return entityManager.find(custCredModel.class, id);
	}

	@Override
	@Transactional
	public custCredModel getCustomer(String custEmail, String custPassword) {
		try {
			String query = "SELECT c FROM custCredModel c WHERE c.custEmail = :custEmail AND c.custPassword = :custPassword";
			TypedQuery<custCredModel> typedQuery = entityManager.createQuery(query, custCredModel.class);
			typedQuery.setParameter("custEmail", custEmail);
			typedQuery.setParameter("custPassword", custPassword);
			List<custCredModel> results = typedQuery.getResultList();
			if (results.isEmpty()) {
				return null;
			}
			return results.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public void updatecustomer(custCredModel customer) {
		entityManager.merge(customer);
	}

	@Override
	@Transactional
	public void updatePassword(String password, String email) {
		try {
			String query = "UPDATE custCredModel SET custPassword = :password WHERE custEmail = :email";
			entityManager.createQuery(query).setParameter("password", password).setParameter("email", email)
					.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public custCredModel getCustomerByEmail(String email) {
		try {
			String query = "SELECT c FROM custCredModel c WHERE c.custEmail = :email";
			return entityManager.createQuery(query, custCredModel.class).setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}