/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author roger
 * @param <T>
 */
public class ClasseDAO<T> {

    private Session sesion;
    private Transaction tx;
    
    private Class p;

    
    public ClasseDAO() {
    }
    
    public ClasseDAO(Class<T> p) {
        this.p = p;
        this.sesion=sesion;
    }
    
    public long guarda(T objecte) throws HibernateException {
        long id = 0;

        try {
            iniciaOperacio();
            id = Long.parseLong(String.valueOf(sesion.save(objecte)));
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    public void actualitza(T objecte) throws HibernateException {
        try {
            iniciaOperacio();
            sesion.update(objecte);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void elimina(T objecte) throws HibernateException {
        try {
            iniciaOperacio();
            sesion.delete(objecte);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public T obte(int idObjecte) throws HibernateException {
        T objecte = null;
        try {
            iniciaOperacio();
            objecte = (T) sesion.get(p, idObjecte);
            tx.commit();
        } finally {
            sesion.close();
        }

        return objecte;
    }

    public List<T> obtenLlista() throws HibernateException {
        ArrayList<T> llista = new ArrayList<>();
        try {
            iniciaOperacio();
            llista = (ArrayList) sesion.createQuery("from "+p.getSimpleName()).list();
            tx.commit();
        } finally {
            sesion.close();
        }

        return llista;
    }

    private void iniciaOperacio() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void tractaExcepcio(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error a la capa d'accés a dades", he);
    }
}
