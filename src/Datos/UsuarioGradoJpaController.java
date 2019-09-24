/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Logica_Negocio.UsuarioGrado;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JoseM
 */
public class UsuarioGradoJpaController implements Serializable {

    public UsuarioGradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioGrado usuarioGrado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarioGrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioGrado(usuarioGrado.getIdUsuarioGrado()) != null) {
                throw new PreexistingEntityException("UsuarioGrado " + usuarioGrado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioGrado usuarioGrado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarioGrado = em.merge(usuarioGrado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = usuarioGrado.getIdUsuarioGrado();
                if (findUsuarioGrado(id) == null) {
                    throw new NonexistentEntityException("The usuarioGrado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioGrado usuarioGrado;
            try {
                usuarioGrado = em.getReference(UsuarioGrado.class, id);
                usuarioGrado.getIdUsuarioGrado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioGrado with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarioGrado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioGrado> findUsuarioGradoEntities() {
        return findUsuarioGradoEntities(true, -1, -1);
    }

    public List<UsuarioGrado> findUsuarioGradoEntities(int maxResults, int firstResult) {
        return findUsuarioGradoEntities(false, maxResults, firstResult);
    }

    private List<UsuarioGrado> findUsuarioGradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioGrado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UsuarioGrado findUsuarioGrado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioGrado.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioGradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioGrado> rt = cq.from(UsuarioGrado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
