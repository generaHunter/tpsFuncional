/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Logica_Negocio.Grado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.UsuarioGrado;
import java.util.ArrayList;
import java.util.List;
import Logica_Negocio.Matricula;
import Logica_Negocio.MateriaUsuario;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JoseM
 */
public class GradoJpaController implements Serializable {

    public GradoJpaController() {
         this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grado grado) {
        if (grado.getUsuarioGradoList() == null) {
            grado.setUsuarioGradoList(new ArrayList<UsuarioGrado>());
        }
        if (grado.getMatriculaList() == null) {
            grado.setMatriculaList(new ArrayList<Matricula>());
        }
        if (grado.getMateriaUsuario1List() == null) {
            grado.setMateriaUsuario1List(new ArrayList<MateriaUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UsuarioGrado> attachedUsuarioGradoList = new ArrayList<UsuarioGrado>();
            for (UsuarioGrado usuarioGradoListUsuarioGradoToAttach : grado.getUsuarioGradoList()) {
                usuarioGradoListUsuarioGradoToAttach = em.getReference(usuarioGradoListUsuarioGradoToAttach.getClass(), usuarioGradoListUsuarioGradoToAttach.getIdUsuarioGrado());
                attachedUsuarioGradoList.add(usuarioGradoListUsuarioGradoToAttach);
            }
            grado.setUsuarioGradoList(attachedUsuarioGradoList);
            List<Matricula> attachedMatriculaList = new ArrayList<Matricula>();
            for (Matricula matriculaListMatriculaToAttach : grado.getMatriculaList()) {
                matriculaListMatriculaToAttach = em.getReference(matriculaListMatriculaToAttach.getClass(), matriculaListMatriculaToAttach.getIdMatricula());
                attachedMatriculaList.add(matriculaListMatriculaToAttach);
            }
            grado.setMatriculaList(attachedMatriculaList);
            List<MateriaUsuario> attachedMateriaUsuario1List = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuario1ListMateriaUsuario1ToAttach : grado.getMateriaUsuario1List()) {
                materiaUsuario1ListMateriaUsuario1ToAttach = em.getReference(materiaUsuario1ListMateriaUsuario1ToAttach.getClass(), materiaUsuario1ListMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuario1List.add(materiaUsuario1ListMateriaUsuario1ToAttach);
            }
            grado.setMateriaUsuario1List(attachedMateriaUsuario1List);
            em.persist(grado);
            for (UsuarioGrado usuarioGradoListUsuarioGrado : grado.getUsuarioGradoList()) {
                Grado oldIdGradoOfUsuarioGradoListUsuarioGrado = usuarioGradoListUsuarioGrado.getIdGrado();
                usuarioGradoListUsuarioGrado.setIdGrado(grado);
                usuarioGradoListUsuarioGrado = em.merge(usuarioGradoListUsuarioGrado);
                if (oldIdGradoOfUsuarioGradoListUsuarioGrado != null) {
                    oldIdGradoOfUsuarioGradoListUsuarioGrado.getUsuarioGradoList().remove(usuarioGradoListUsuarioGrado);
                    oldIdGradoOfUsuarioGradoListUsuarioGrado = em.merge(oldIdGradoOfUsuarioGradoListUsuarioGrado);
                }
            }
            for (Matricula matriculaListMatricula : grado.getMatriculaList()) {
                Grado oldIdGradoOfMatriculaListMatricula = matriculaListMatricula.getIdGrado();
                matriculaListMatricula.setIdGrado(grado);
                matriculaListMatricula = em.merge(matriculaListMatricula);
                if (oldIdGradoOfMatriculaListMatricula != null) {
                    oldIdGradoOfMatriculaListMatricula.getMatriculaList().remove(matriculaListMatricula);
                    oldIdGradoOfMatriculaListMatricula = em.merge(oldIdGradoOfMatriculaListMatricula);
                }
            }
            for (MateriaUsuario materiaUsuario1ListMateriaUsuario1 : grado.getMateriaUsuario1List()) {
                Grado oldIdGradoOfMateriaUsuario1ListMateriaUsuario1 = materiaUsuario1ListMateriaUsuario1.getIdGrado();
                materiaUsuario1ListMateriaUsuario1.setIdGrado(grado);
                materiaUsuario1ListMateriaUsuario1 = em.merge(materiaUsuario1ListMateriaUsuario1);
                if (oldIdGradoOfMateriaUsuario1ListMateriaUsuario1 != null) {
                    oldIdGradoOfMateriaUsuario1ListMateriaUsuario1.getMateriaUsuario1List().remove(materiaUsuario1ListMateriaUsuario1);
                    oldIdGradoOfMateriaUsuario1ListMateriaUsuario1 = em.merge(oldIdGradoOfMateriaUsuario1ListMateriaUsuario1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grado grado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grado persistentGrado = em.find(Grado.class, grado.getIdGrado());
            List<UsuarioGrado> usuarioGradoListOld = persistentGrado.getUsuarioGradoList();
            List<UsuarioGrado> usuarioGradoListNew = grado.getUsuarioGradoList();
            List<Matricula> matriculaListOld = persistentGrado.getMatriculaList();
            List<Matricula> matriculaListNew = grado.getMatriculaList();
            List<MateriaUsuario> materiaUsuario1ListOld = persistentGrado.getMateriaUsuario1List();
            List<MateriaUsuario> materiaUsuario1ListNew = grado.getMateriaUsuario1List();
            List<String> illegalOrphanMessages = null;
            for (UsuarioGrado usuarioGradoListOldUsuarioGrado : usuarioGradoListOld) {
                if (!usuarioGradoListNew.contains(usuarioGradoListOldUsuarioGrado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioGrado " + usuarioGradoListOldUsuarioGrado + " since its idGrado field is not nullable.");
                }
            }
            for (Matricula matriculaListOldMatricula : matriculaListOld) {
                if (!matriculaListNew.contains(matriculaListOldMatricula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Matricula " + matriculaListOldMatricula + " since its idGrado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UsuarioGrado> attachedUsuarioGradoListNew = new ArrayList<UsuarioGrado>();
            for (UsuarioGrado usuarioGradoListNewUsuarioGradoToAttach : usuarioGradoListNew) {
                usuarioGradoListNewUsuarioGradoToAttach = em.getReference(usuarioGradoListNewUsuarioGradoToAttach.getClass(), usuarioGradoListNewUsuarioGradoToAttach.getIdUsuarioGrado());
                attachedUsuarioGradoListNew.add(usuarioGradoListNewUsuarioGradoToAttach);
            }
            usuarioGradoListNew = attachedUsuarioGradoListNew;
            grado.setUsuarioGradoList(usuarioGradoListNew);
            List<Matricula> attachedMatriculaListNew = new ArrayList<Matricula>();
            for (Matricula matriculaListNewMatriculaToAttach : matriculaListNew) {
                matriculaListNewMatriculaToAttach = em.getReference(matriculaListNewMatriculaToAttach.getClass(), matriculaListNewMatriculaToAttach.getIdMatricula());
                attachedMatriculaListNew.add(matriculaListNewMatriculaToAttach);
            }
            matriculaListNew = attachedMatriculaListNew;
            grado.setMatriculaList(matriculaListNew);
            List<MateriaUsuario> attachedMateriaUsuario1ListNew = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuario1ListNewMateriaUsuario1ToAttach : materiaUsuario1ListNew) {
                materiaUsuario1ListNewMateriaUsuario1ToAttach = em.getReference(materiaUsuario1ListNewMateriaUsuario1ToAttach.getClass(), materiaUsuario1ListNewMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuario1ListNew.add(materiaUsuario1ListNewMateriaUsuario1ToAttach);
            }
            materiaUsuario1ListNew = attachedMateriaUsuario1ListNew;
            grado.setMateriaUsuario1List(materiaUsuario1ListNew);
            grado = em.merge(grado);
            for (UsuarioGrado usuarioGradoListNewUsuarioGrado : usuarioGradoListNew) {
                if (!usuarioGradoListOld.contains(usuarioGradoListNewUsuarioGrado)) {
                    Grado oldIdGradoOfUsuarioGradoListNewUsuarioGrado = usuarioGradoListNewUsuarioGrado.getIdGrado();
                    usuarioGradoListNewUsuarioGrado.setIdGrado(grado);
                    usuarioGradoListNewUsuarioGrado = em.merge(usuarioGradoListNewUsuarioGrado);
                    if (oldIdGradoOfUsuarioGradoListNewUsuarioGrado != null && !oldIdGradoOfUsuarioGradoListNewUsuarioGrado.equals(grado)) {
                        oldIdGradoOfUsuarioGradoListNewUsuarioGrado.getUsuarioGradoList().remove(usuarioGradoListNewUsuarioGrado);
                        oldIdGradoOfUsuarioGradoListNewUsuarioGrado = em.merge(oldIdGradoOfUsuarioGradoListNewUsuarioGrado);
                    }
                }
            }
            for (Matricula matriculaListNewMatricula : matriculaListNew) {
                if (!matriculaListOld.contains(matriculaListNewMatricula)) {
                    Grado oldIdGradoOfMatriculaListNewMatricula = matriculaListNewMatricula.getIdGrado();
                    matriculaListNewMatricula.setIdGrado(grado);
                    matriculaListNewMatricula = em.merge(matriculaListNewMatricula);
                    if (oldIdGradoOfMatriculaListNewMatricula != null && !oldIdGradoOfMatriculaListNewMatricula.equals(grado)) {
                        oldIdGradoOfMatriculaListNewMatricula.getMatriculaList().remove(matriculaListNewMatricula);
                        oldIdGradoOfMatriculaListNewMatricula = em.merge(oldIdGradoOfMatriculaListNewMatricula);
                    }
                }
            }
            for (MateriaUsuario materiaUsuario1ListOldMateriaUsuario1 : materiaUsuario1ListOld) {
                if (!materiaUsuario1ListNew.contains(materiaUsuario1ListOldMateriaUsuario1)) {
                    materiaUsuario1ListOldMateriaUsuario1.setIdGrado(null);
                    materiaUsuario1ListOldMateriaUsuario1 = em.merge(materiaUsuario1ListOldMateriaUsuario1);
                }
            }
            for (MateriaUsuario materiaUsuario1ListNewMateriaUsuario1 : materiaUsuario1ListNew) {
                if (!materiaUsuario1ListOld.contains(materiaUsuario1ListNewMateriaUsuario1)) {
                    Grado oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1 = materiaUsuario1ListNewMateriaUsuario1.getIdGrado();
                    materiaUsuario1ListNewMateriaUsuario1.setIdGrado(grado);
                    materiaUsuario1ListNewMateriaUsuario1 = em.merge(materiaUsuario1ListNewMateriaUsuario1);
                    if (oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1 != null && !oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1.equals(grado)) {
                        oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1.getMateriaUsuario1List().remove(materiaUsuario1ListNewMateriaUsuario1);
                        oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1 = em.merge(oldIdGradoOfMateriaUsuario1ListNewMateriaUsuario1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = grado.getIdGrado();
                if (findGrado(id) == null) {
                    throw new NonexistentEntityException("The grado with id " + id + " no longer exists.");
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
            Grado grado;
            try {
                grado = em.getReference(Grado.class, id);
                grado.getIdGrado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UsuarioGrado> usuarioGradoListOrphanCheck = grado.getUsuarioGradoList();
            for (UsuarioGrado usuarioGradoListOrphanCheckUsuarioGrado : usuarioGradoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grado (" + grado + ") cannot be destroyed since the UsuarioGrado " + usuarioGradoListOrphanCheckUsuarioGrado + " in its usuarioGradoList field has a non-nullable idGrado field.");
            }
            List<Matricula> matriculaListOrphanCheck = grado.getMatriculaList();
            for (Matricula matriculaListOrphanCheckMatricula : matriculaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grado (" + grado + ") cannot be destroyed since the Matricula " + matriculaListOrphanCheckMatricula + " in its matriculaList field has a non-nullable idGrado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MateriaUsuario> materiaUsuario1List = grado.getMateriaUsuario1List();
            for (MateriaUsuario materiaUsuario1ListMateriaUsuario1 : materiaUsuario1List) {
                materiaUsuario1ListMateriaUsuario1.setIdGrado(null);
                materiaUsuario1ListMateriaUsuario1 = em.merge(materiaUsuario1ListMateriaUsuario1);
            }
            em.remove(grado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grado> findGradoEntities() {
        return findGradoEntities(true, -1, -1);
    }

    public List<Grado> findGradoEntities(int maxResults, int firstResult) {
        return findGradoEntities(false, maxResults, firstResult);
    }

    private List<Grado> findGradoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grado.class));
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

    public Grado findGrado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grado.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grado> rt = cq.from(Grado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
