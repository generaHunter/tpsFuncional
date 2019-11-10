/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Logica_Negocio.Materia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.MateriaUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JoseM
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController() {
         this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) {
        if (materia.getMateriaUsuarioList() == null) {
            materia.setMateriaUsuarioList(new ArrayList<MateriaUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MateriaUsuario> attachedMateriaUsuarioList = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuarioListMateriaUsuario1ToAttach : materia.getMateriaUsuarioList()) {
                materiaUsuarioListMateriaUsuario1ToAttach = em.getReference(materiaUsuarioListMateriaUsuario1ToAttach.getClass(), materiaUsuarioListMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuarioList.add(materiaUsuarioListMateriaUsuario1ToAttach);
            }
            materia.setMateriaUsuarioList(attachedMateriaUsuarioList);
            em.persist(materia);
            for (MateriaUsuario materiaUsuarioListMateriaUsuario1 : materia.getMateriaUsuarioList()) {
                Materia oldIdMateriaOfMateriaUsuarioListMateriaUsuario1 = materiaUsuarioListMateriaUsuario1.getIdMateria();
                materiaUsuarioListMateriaUsuario1.setIdMateria(materia);
                materiaUsuarioListMateriaUsuario1 = em.merge(materiaUsuarioListMateriaUsuario1);
                if (oldIdMateriaOfMateriaUsuarioListMateriaUsuario1 != null) {
                    oldIdMateriaOfMateriaUsuarioListMateriaUsuario1.getMateriaUsuarioList().remove(materiaUsuarioListMateriaUsuario1);
                    oldIdMateriaOfMateriaUsuarioListMateriaUsuario1 = em.merge(oldIdMateriaOfMateriaUsuarioListMateriaUsuario1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getIdMateria());
            List<MateriaUsuario> materiaUsuarioListOld = persistentMateria.getMateriaUsuarioList();
            List<MateriaUsuario> materiaUsuarioListNew = materia.getMateriaUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (MateriaUsuario materiaUsuarioListOldMateriaUsuario1 : materiaUsuarioListOld) {
                if (!materiaUsuarioListNew.contains(materiaUsuarioListOldMateriaUsuario1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MateriaUsuario1 " + materiaUsuarioListOldMateriaUsuario1 + " since its idMateria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MateriaUsuario> attachedMateriaUsuarioListNew = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuarioListNewMateriaUsuario1ToAttach : materiaUsuarioListNew) {
                materiaUsuarioListNewMateriaUsuario1ToAttach = em.getReference(materiaUsuarioListNewMateriaUsuario1ToAttach.getClass(), materiaUsuarioListNewMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuarioListNew.add(materiaUsuarioListNewMateriaUsuario1ToAttach);
            }
            materiaUsuarioListNew = attachedMateriaUsuarioListNew;
            materia.setMateriaUsuarioList(materiaUsuarioListNew);
            materia = em.merge(materia);
            for (MateriaUsuario materiaUsuarioListNewMateriaUsuario1 : materiaUsuarioListNew) {
                if (!materiaUsuarioListOld.contains(materiaUsuarioListNewMateriaUsuario1)) {
                    Materia oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1 = materiaUsuarioListNewMateriaUsuario1.getIdMateria();
                    materiaUsuarioListNewMateriaUsuario1.setIdMateria(materia);
                    materiaUsuarioListNewMateriaUsuario1 = em.merge(materiaUsuarioListNewMateriaUsuario1);
                    if (oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1 != null && !oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1.equals(materia)) {
                        oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1.getMateriaUsuarioList().remove(materiaUsuarioListNewMateriaUsuario1);
                        oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1 = em.merge(oldIdMateriaOfMateriaUsuarioListNewMateriaUsuario1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = materia.getIdMateria();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getIdMateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MateriaUsuario> materiaUsuarioListOrphanCheck = materia.getMateriaUsuarioList();
            for (MateriaUsuario materiaUsuarioListOrphanCheckMateriaUsuario1 : materiaUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materia (" + materia + ") cannot be destroyed since the MateriaUsuario1 " + materiaUsuarioListOrphanCheckMateriaUsuario1 + " in its materiaUsuarioList field has a non-nullable idMateria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
