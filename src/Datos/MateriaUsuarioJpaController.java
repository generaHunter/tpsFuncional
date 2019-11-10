/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.Materia;
import Logica_Negocio.Usuario;
import Logica_Negocio.Grado;
import Logica_Negocio.AlumnoProfesor;
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
public class MateriaUsuarioJpaController implements Serializable {

    public MateriaUsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaUsuario materiaUsuario1) {
        if (materiaUsuario1.getAlumnoProfesorList() == null) {
            materiaUsuario1.setAlumnoProfesorList(new ArrayList<AlumnoProfesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia idMateria = materiaUsuario1.getIdMateria();
            if (idMateria != null) {
                idMateria = em.getReference(idMateria.getClass(), idMateria.getIdMateria());
                materiaUsuario1.setIdMateria(idMateria);
            }
            Usuario idUsuario = materiaUsuario1.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                materiaUsuario1.setIdUsuario(idUsuario);
            }
            Grado idGrado = materiaUsuario1.getIdGrado();
            if (idGrado != null) {
                idGrado = em.getReference(idGrado.getClass(), idGrado.getIdGrado());
                materiaUsuario1.setIdGrado(idGrado);
            }
            List<AlumnoProfesor> attachedAlumnoProfesorList = new ArrayList<AlumnoProfesor>();
            for (AlumnoProfesor alumnoProfesorListAlumnoProfesorToAttach : materiaUsuario1.getAlumnoProfesorList()) {
                alumnoProfesorListAlumnoProfesorToAttach = em.getReference(alumnoProfesorListAlumnoProfesorToAttach.getClass(), alumnoProfesorListAlumnoProfesorToAttach.getIdAlumnoProfesor());
                attachedAlumnoProfesorList.add(alumnoProfesorListAlumnoProfesorToAttach);
            }
            materiaUsuario1.setAlumnoProfesorList(attachedAlumnoProfesorList);
            em.persist(materiaUsuario1);
            if (idMateria != null) {
                idMateria.getMateriaUsuarioList().add(materiaUsuario1);
                idMateria = em.merge(idMateria);
            }
            if (idUsuario != null) {
                idUsuario.getMateriaUsuarioList().add(materiaUsuario1);
                idUsuario = em.merge(idUsuario);
            }
            if (idGrado != null) {
                idGrado.getMateriaUsuario1List().add(materiaUsuario1);
                idGrado = em.merge(idGrado);
            }
            for (AlumnoProfesor alumnoProfesorListAlumnoProfesor : materiaUsuario1.getAlumnoProfesorList()) {
                MateriaUsuario oldIdMatusuOfAlumnoProfesorListAlumnoProfesor = alumnoProfesorListAlumnoProfesor.getIdMatusu();
                alumnoProfesorListAlumnoProfesor.setIdMatusu(materiaUsuario1);
                alumnoProfesorListAlumnoProfesor = em.merge(alumnoProfesorListAlumnoProfesor);
                if (oldIdMatusuOfAlumnoProfesorListAlumnoProfesor != null) {
                    oldIdMatusuOfAlumnoProfesorListAlumnoProfesor.getAlumnoProfesorList().remove(alumnoProfesorListAlumnoProfesor);
                    oldIdMatusuOfAlumnoProfesorListAlumnoProfesor = em.merge(oldIdMatusuOfAlumnoProfesorListAlumnoProfesor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaUsuario materiaUsuario1) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaUsuario persistentMateriaUsuario1 = em.find(MateriaUsuario.class, materiaUsuario1.getIdMatusu());
            Materia idMateriaOld = persistentMateriaUsuario1.getIdMateria();
            Materia idMateriaNew = materiaUsuario1.getIdMateria();
            Usuario idUsuarioOld = persistentMateriaUsuario1.getIdUsuario();
            Usuario idUsuarioNew = materiaUsuario1.getIdUsuario();
            Grado idGradoOld = persistentMateriaUsuario1.getIdGrado();
            Grado idGradoNew = materiaUsuario1.getIdGrado();
            List<AlumnoProfesor> alumnoProfesorListOld = persistentMateriaUsuario1.getAlumnoProfesorList();
            List<AlumnoProfesor> alumnoProfesorListNew = materiaUsuario1.getAlumnoProfesorList();
            List<String> illegalOrphanMessages = null;
            for (AlumnoProfesor alumnoProfesorListOldAlumnoProfesor : alumnoProfesorListOld) {
                if (!alumnoProfesorListNew.contains(alumnoProfesorListOldAlumnoProfesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AlumnoProfesor " + alumnoProfesorListOldAlumnoProfesor + " since its idMatusu field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMateriaNew != null) {
                idMateriaNew = em.getReference(idMateriaNew.getClass(), idMateriaNew.getIdMateria());
                materiaUsuario1.setIdMateria(idMateriaNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                materiaUsuario1.setIdUsuario(idUsuarioNew);
            }
            if (idGradoNew != null) {
                idGradoNew = em.getReference(idGradoNew.getClass(), idGradoNew.getIdGrado());
                materiaUsuario1.setIdGrado(idGradoNew);
            }
            List<AlumnoProfesor> attachedAlumnoProfesorListNew = new ArrayList<AlumnoProfesor>();
            for (AlumnoProfesor alumnoProfesorListNewAlumnoProfesorToAttach : alumnoProfesorListNew) {
                alumnoProfesorListNewAlumnoProfesorToAttach = em.getReference(alumnoProfesorListNewAlumnoProfesorToAttach.getClass(), alumnoProfesorListNewAlumnoProfesorToAttach.getIdAlumnoProfesor());
                attachedAlumnoProfesorListNew.add(alumnoProfesorListNewAlumnoProfesorToAttach);
            }
            alumnoProfesorListNew = attachedAlumnoProfesorListNew;
            materiaUsuario1.setAlumnoProfesorList(alumnoProfesorListNew);
            materiaUsuario1 = em.merge(materiaUsuario1);
            if (idMateriaOld != null && !idMateriaOld.equals(idMateriaNew)) {
                idMateriaOld.getMateriaUsuarioList().remove(materiaUsuario1);
                idMateriaOld = em.merge(idMateriaOld);
            }
            if (idMateriaNew != null && !idMateriaNew.equals(idMateriaOld)) {
                idMateriaNew.getMateriaUsuarioList().add(materiaUsuario1);
                idMateriaNew = em.merge(idMateriaNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getMateriaUsuarioList().remove(materiaUsuario1);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getMateriaUsuarioList().add(materiaUsuario1);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            if (idGradoOld != null && !idGradoOld.equals(idGradoNew)) {
                idGradoOld.getMateriaUsuario1List().remove(materiaUsuario1);
                idGradoOld = em.merge(idGradoOld);
            }
            if (idGradoNew != null && !idGradoNew.equals(idGradoOld)) {
                idGradoNew.getMateriaUsuario1List().add(materiaUsuario1);
                idGradoNew = em.merge(idGradoNew);
            }
            for (AlumnoProfesor alumnoProfesorListNewAlumnoProfesor : alumnoProfesorListNew) {
                if (!alumnoProfesorListOld.contains(alumnoProfesorListNewAlumnoProfesor)) {
                    MateriaUsuario oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor = alumnoProfesorListNewAlumnoProfesor.getIdMatusu();
                    alumnoProfesorListNewAlumnoProfesor.setIdMatusu(materiaUsuario1);
                    alumnoProfesorListNewAlumnoProfesor = em.merge(alumnoProfesorListNewAlumnoProfesor);
                    if (oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor != null && !oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor.equals(materiaUsuario1)) {
                        oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor.getAlumnoProfesorList().remove(alumnoProfesorListNewAlumnoProfesor);
                        oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor = em.merge(oldIdMatusuOfAlumnoProfesorListNewAlumnoProfesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = materiaUsuario1.getIdMatusu();
                if (findMateriaUsuario1(id) == null) {
                    throw new NonexistentEntityException("The materiaUsuario1 with id " + id + " no longer exists.");
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
            MateriaUsuario materiaUsuario1;
            try {
                materiaUsuario1 = em.getReference(MateriaUsuario.class, id);
                materiaUsuario1.getIdMatusu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaUsuario1 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AlumnoProfesor> alumnoProfesorListOrphanCheck = materiaUsuario1.getAlumnoProfesorList();
            for (AlumnoProfesor alumnoProfesorListOrphanCheckAlumnoProfesor : alumnoProfesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MateriaUsuario1 (" + materiaUsuario1 + ") cannot be destroyed since the AlumnoProfesor " + alumnoProfesorListOrphanCheckAlumnoProfesor + " in its alumnoProfesorList field has a non-nullable idMatusu field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Materia idMateria = materiaUsuario1.getIdMateria();
            if (idMateria != null) {
                idMateria.getMateriaUsuarioList().remove(materiaUsuario1);
                idMateria = em.merge(idMateria);
            }
            Usuario idUsuario = materiaUsuario1.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getMateriaUsuarioList().remove(materiaUsuario1);
                idUsuario = em.merge(idUsuario);
            }
            Grado idGrado = materiaUsuario1.getIdGrado();
            if (idGrado != null) {
                idGrado.getMateriaUsuario1List().remove(materiaUsuario1);
                idGrado = em.merge(idGrado);
            }
            em.remove(materiaUsuario1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaUsuario> findMateriaUsuario1Entities() {
        return findMateriaUsuario1Entities(true, -1, -1);
    }

    public List<MateriaUsuario> findMateriaUsuario1Entities(int maxResults, int firstResult) {
        return findMateriaUsuario1Entities(false, maxResults, firstResult);
    }

    private List<MateriaUsuario> findMateriaUsuario1Entities(boolean all, int maxResults, int firstResult) {
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

    public MateriaUsuario findMateriaUsuario1(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaUsuario1Count() {
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
