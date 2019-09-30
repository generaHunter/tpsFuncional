/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.Materia;
import Logica_Negocio.MateriaUsuario;
import Logica_Negocio.Usuario;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JoseM
 */
public class MateriaUsuarioJpaController implements Serializable {

    public MateriaUsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaUsuario materiaUsuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia idMateria = materiaUsuario.getIdMateria();
            if (idMateria != null) {
                idMateria = em.getReference(idMateria.getClass(), idMateria.getIdMateria());
                materiaUsuario.setIdMateria(idMateria);
            }
            Usuario idUsuario = materiaUsuario.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                materiaUsuario.setIdUsuario(idUsuario);
            }
            em.persist(materiaUsuario);
            if (idMateria != null) {
                idMateria.getMateriaUsuarioList().add(materiaUsuario);
                idMateria = em.merge(idMateria);
            }
            if (idUsuario != null) {
                idUsuario.getMateriaUsuarioList().add(materiaUsuario);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaUsuario(materiaUsuario.getIdMatusu()) != null) {
                throw new PreexistingEntityException("MateriaUsuario " + materiaUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaUsuario materiaUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaUsuario persistentMateriaUsuario = em.find(MateriaUsuario.class, materiaUsuario.getIdMatusu());
            Materia idMateriaOld = persistentMateriaUsuario.getIdMateria();
            Materia idMateriaNew = materiaUsuario.getIdMateria();
            Usuario idUsuarioOld = persistentMateriaUsuario.getIdUsuario();
            Usuario idUsuarioNew = materiaUsuario.getIdUsuario();
            if (idMateriaNew != null) {
                idMateriaNew = em.getReference(idMateriaNew.getClass(), idMateriaNew.getIdMateria());
                materiaUsuario.setIdMateria(idMateriaNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                materiaUsuario.setIdUsuario(idUsuarioNew);
            }
            materiaUsuario = em.merge(materiaUsuario);
            if (idMateriaOld != null && !idMateriaOld.equals(idMateriaNew)) {
                idMateriaOld.getMateriaUsuarioList().remove(materiaUsuario);
                idMateriaOld = em.merge(idMateriaOld);
            }
            if (idMateriaNew != null && !idMateriaNew.equals(idMateriaOld)) {
                idMateriaNew.getMateriaUsuarioList().add(materiaUsuario);
                idMateriaNew = em.merge(idMateriaNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getMateriaUsuarioList().remove(materiaUsuario);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getMateriaUsuarioList().add(materiaUsuario);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = materiaUsuario.getIdMatusu();
                if (findMateriaUsuario(id) == null) {
                    throw new NonexistentEntityException("The materiaUsuario with id " + id + " no longer exists.");
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
            MateriaUsuario materiaUsuario;
            try {
                materiaUsuario = em.getReference(MateriaUsuario.class, id);
                materiaUsuario.getIdMatusu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaUsuario with id " + id + " no longer exists.", enfe);
            }
            Materia idMateria = materiaUsuario.getIdMateria();
            if (idMateria != null) {
                idMateria.getMateriaUsuarioList().remove(materiaUsuario);
                idMateria = em.merge(idMateria);
            }
            Usuario idUsuario = materiaUsuario.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getMateriaUsuarioList().remove(materiaUsuario);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(materiaUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaUsuario> findMateriaUsuarioEntities() {
        return findMateriaUsuarioEntities(true, -1, -1);
    }

    public List<MateriaUsuario> findMateriaUsuarioEntities(int maxResults, int firstResult) {
        return findMateriaUsuarioEntities(false, maxResults, firstResult);
    }

    private List<MateriaUsuario> findMateriaUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaUsuario.class));
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

    public MateriaUsuario findMateriaUsuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaUsuario> rt = cq.from(MateriaUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
