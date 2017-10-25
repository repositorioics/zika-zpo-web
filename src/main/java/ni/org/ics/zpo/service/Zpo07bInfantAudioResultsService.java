package ni.org.ics.zpo.service;

import ni.org.ics.zpo.domain.Zpo07bInfantAudioResults;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ics on 20/6/2017.
 * V1.0
 */
@Service("zpo07bInfantAudioResultsService")
@Transactional
public class Zpo07bInfantAudioResultsService {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retorna todos los formularios Zpo07bInfantAudioResults
     * @return una lista de Zpo07bInfantAudioResults
     */
    @SuppressWarnings("unchecked")
    public List<Zpo07bInfantAudioResults> getZpo07bInfantAudioResults(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Zpo07bInfantAudioResults");
        return query.list();
    }

    /**
     * Retorna todos los formularios Zp07bInfantAudioResult
     * @return una lista de Zp07bInfantAudioResult
     */
    @SuppressWarnings("unchecked")
    public List<Zpo07bInfantAudioResults> getZpo07bInfantAudioResultstByUser(String username){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Zpo07bInfantAudioResults zpo07 where zpo07.pasive = '0' and zpo07.recordId in (select zpo00.recordId from Zpo00Screening zpo00 where zpo00.pasive = '0' and zpo00.preScreenId in (select recId from ZpoPreScreening zpPre where zpPre.pasive = '0' and zpPre.cs in " +
                "(Select uc.centro.cs from UserCenter uc where uc.user.username =:usuarioactual and uc.pasive = '0')))");
        query.setParameter("usuarioactual",username);
        return query.list();
    }

    /**
     * Guardar un formulario Zpo07bInfantAudioResults
     * @param zpo07bInfantAudioResults a guardar
     */
    public void saveZpo07bInfantAudioResults(Zpo07bInfantAudioResults zpo07bInfantAudioResults){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(zpo07bInfantAudioResults);
    }
}
