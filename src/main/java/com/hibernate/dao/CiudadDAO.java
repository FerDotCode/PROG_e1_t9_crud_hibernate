package com.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Ciudad;
import com.hibernate.util.HibernateUtil;

public class CiudadDAO {

	public void insertCiudad(Ciudad p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void updateCiudad(Ciudad p) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public void deleteCiudad(int codigo) {
		Transaction transaction = null;
		Ciudad p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			p = session.get(Ciudad.class, codigo);
			session.remove(p);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	public Ciudad selectCiudadById(int codigo) {
		Transaction transaction = null;
		Ciudad p = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			p = session.get(Ciudad.class, codigo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return p;
	}

	public List<Ciudad> selectAllCiudads() {
		Transaction transaction = null;
		List<Ciudad> ciudades = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			ciudades = session.createQuery("FROM Ciudad", Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return ciudades;
	}

}
