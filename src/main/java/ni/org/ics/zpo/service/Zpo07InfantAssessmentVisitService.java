package ni.org.ics.zpo.service;

import ni.org.ics.zpo.domain.Zpo07InfantAssessmentVisit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by FIRSTICT on 2/1/2017.
 * V1.0
 */
@Service("zpo07InfantAssessmentVisitService")
@Transactional
public class Zpo07InfantAssessmentVisitService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retorna todos los formularios Zpo07InfantAssessmentVisit
     * @return una lista de Zpo07InfantAssessmentVisit
     */
    @SuppressWarnings("unchecked")
    public List<Zpo07InfantAssessmentVisit> getZpo07InfantAssessmentVisit(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Zpo07InfantAssessmentVisit");
        return query.list();
    }

    /**
     * Retorna todos los formularios Zpo07InfantAssessmentVisit
     * @return una lista de Zpo07InfantAssessmentVisit
     */
    @SuppressWarnings("unchecked")
    public List<Zpo07InfantAssessmentVisit> getZpo07InfantAssessmentVisitByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Zpo07InfantAssessmentVisit zpo07 where zpo07.pasive = '0' and zpo07.recordId in (select zpoI.recordId from Zpo00Screening zpo00, ZpoInfantData zpoI where zpo00.recordId = zpoI.pregnantId and zpo00.pasive = '0')");
        //query.setParameter("usuarioactual",username);
        return query.list();
    }

    /**
     * Guardar un formulario Zpo07InfantAssessmentVisit
     * @param zpo07InfantAssessmentVisit a guardar
     */
    public void saveZpo07InfantAssessmentVisit(Zpo07InfantAssessmentVisit zpo07InfantAssessmentVisit){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(zpo07InfantAssessmentVisit);
    }
}
