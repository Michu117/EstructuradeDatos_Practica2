package unl.dance.base.controller.dao.dao_models;

import unl.dance.base.controller.dao.AdapterDao;
import unl.dance.base.models.Cancion;

public class DaoCancion extends AdapterDao<Cancion> {
    private Cancion obj;

    public DaoCancion() {
        super(Cancion.class);
        // TODO Auto-generated constructor stub
    }

    public Cancion getObj() {
        if (obj == null)
            this.obj = new Cancion();
        return this.obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    
}

