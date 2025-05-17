package unl.dance.base.controller.dao.dao_models;

import unl.dance.base.controller.dao.AdapterDao;
import unl.dance.base.models.Cancion;
import unl.dance.base.models.TipoArchivoEnum;

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

    
    public static void main(String[] args) {
        DaoCancion da = new DaoCancion();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Molotov");
        da.getObj().setId_genero(1);
        da.getObj().setDuracion(3);
        da.getObj().setUrl("https://www.youtube.com/watch?v=3Yc2v4g0X1E");
        da.getObj().setTipo(TipoArchivoEnum.FISICO);
        da.getObj().setId_album(1);
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
    }



}

