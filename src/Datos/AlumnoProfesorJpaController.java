/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Logica_Negocio.AlumnoProfesor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.MateriaUsuario;
import Logica_Negocio.Matricula;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JoseM
 */
public class AlumnoProfesorJpaController implements Serializable {

    public AlumnoProfesorJpaController() {
         this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AlumnoProfesor alumnoProfesor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaUsuario idMatusu = alumnoProfesor.getIdMatusu();
            if (idMatusu != null) {
                idMatusu = em.getReference(idMatusu.getClass(), idMatusu.getIdMatusu());
                alumnoProfesor.setIdMatusu(idMatusu);
            }
            Matricula idMatricula = alumnoProfesor.getIdMatricula();
            if (idMatricula != null) {
                idMatricula = em.getReference(idMatricula.getClass(), idMatricula.getIdMatricula());
                alumnoProfesor.setIdMatricula(idMatricula);
            }
            em.persist(alumnoProfesor);
            if (idMatusu != null) {
                idMatusu.getAlumnoProfesorList().add(alumnoProfesor);
                idMatusu = em.merge(idMatusu);
            }
            if (idMatricula != null) {
                idMatricula.getAlumnoProfesorList().add(alumnoProfesor);
                idMatricula = em.merge(idMatricula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumnoProfesor(alumnoProfesor.getIdAlumnoProfesor()) != null) {
                throw new PreexistingEntityException("AlumnoProfesor " + alumnoProfesor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AlumnoProfesor alumnoProfesor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AlumnoProfesor persistentAlumnoProfesor = em.find(AlumnoProfesor.class, alumnoProfesor.getIdAlumnoProfesor());
            MateriaUsuario idMatusuOld = persistentAlumnoProfesor.getIdMatusu();
            MateriaUsuario idMatusuNew = alumnoProfesor.getIdMatusu();
            Matricula idMatriculaOld = persistentAlumnoProfesor.getIdMatricula();
            Matricula idMatriculaNew = alumnoProfesor.getIdMatricula();
            if (idMatusuNew != null) {
                idMatusuNew = em.getReference(idMatusuNew.getClass(), idMatusuNew.getIdMatusu());
                alumnoProfesor.setIdMatusu(idMatusuNew);
            }
            if (idMatriculaNew != null) {
                idMatriculaNew = em.getReference(idMatriculaNew.getClass(), idMatriculaNew.getIdMatricula());
                alumnoProfesor.setIdMatricula(idMatriculaNew);
            }
            alumnoProfesor = em.merge(alumnoProfesor);
            if (idMatusuOld != null && !idMatusuOld.equals(idMatusuNew)) {
                idMatusuOld.getAlumnoProfesorList().remove(alumnoProfesor);
                idMatusuOld = em.merge(idMatusuOld);
            }
            if (idMatusuNew != null && !idMatusuNew.equals(idMatusuOld)) {
                idMatusuNew.getAlumnoProfesorList().add(alumnoProfesor);
                idMatusuNew = em.merge(idMatusuNew);
            }
            if (idMatriculaOld != null && !idMatriculaOld.equals(idMatriculaNew)) {
                idMatriculaOld.getAlumnoProfesorList().remove(alumnoProfesor);
                idMatriculaOld = em.merge(idMatriculaOld);
            }
            if (idMatriculaNew != null && !idMatriculaNew.equals(idMatriculaOld)) {
                idMatriculaNew.getAlumnoProfesorList().add(alumnoProfesor);
                idMatriculaNew = em.merge(idMatriculaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = alumnoProfesor.getIdAlumnoProfesor();
                if (findAlumnoProfesor(id) == null) {
                    throw new NonexistentEntityException("The alumnoProfesor with id " + id + " no longer exists.");
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
            AlumnoProfesor alumnoProfesor;
            try {
                alumnoProfesor = em.getReference(AlumnoProfesor.class, id);
                alumnoProfesor.getIdAlumnoProfesor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumnoProfesor with id " + id + " no longer exists.", enfe);
            }
            MateriaUsuario idMatusu = alumnoProfesor.getIdMatusu();
            if (idMatusu != null) {
                idMatusu.getAlumnoProfesorList().remove(alumnoProfesor);
                idMatusu = em.merge(idMatusu);
            }
            Matricula idMatricula = alumnoProfesor.getIdMatricula();
            if (idMatricula != null) {
                idMatricula.getAlumnoProfesorList().remove(alumnoProfesor);
                idMatricula = em.merge(idMatricula);
            }
            em.remove(alumnoProfesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AlumnoProfesor> findAlumnoProfesorEntities() {
        return findAlumnoProfesorEntities(true, -1, -1);
    }

    public List<AlumnoProfesor> findAlumnoProfesorEntities(int maxResults, int firstResult) {
        return findAlumnoProfesorEntities(false, maxResults, firstResult);
    }

    private List<AlumnoProfesor> findAlumnoProfesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AlumnoProfesor.class));
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

    public AlumnoProfesor findAlumnoProfesor(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AlumnoProfesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoProfesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AlumnoProfesor> rt = cq.from(AlumnoProfesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
