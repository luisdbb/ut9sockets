package tarintro2g;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableModel;

/**
 *
 * @author luis
 */
class Examen implements Serializable {

    Map<String, String> examen = new HashMap<String, String>();
    String nombreAlumno;

    public Examen() {
    }

    public Examen(String nombre) {
        this.nombreAlumno = nombre;
    }

    public Examen(TableModel modelo, String nombre) {
        for (int i = 0; i < 10; i++) {
            if (modelo.getValueAt(i, 1) != null) {
                contestar(String.valueOf(i + 1), modelo.getValueAt(i, 1).toString());
            } else {
                contestar(String.valueOf(i + 1), String.valueOf(0));
            }
        }
        this.nombreAlumno = nombre;
    }

    public Map<String, String> getExamen() {
        return examen;
    }

    public void setExamen(Map<String, String> examen) {
        this.examen = examen;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    private void contestar(String preg, String resp) {
        this.examen.put(preg, resp);
    }

}
