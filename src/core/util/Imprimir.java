package core.util;

import java.io.File;
import java.lang.reflect.Field;

public class Imprimir {
    public static String imprimir (Object entidade){
        Class<?> classe = entidade.getClass();

        StringBuilder resul = new StringBuilder(classe.getSimpleName());
        Field[] campos = classe.getDeclaredFields();

        for(Field campo: campos){
            campo.setAccessible(true);
            String nomeCampo = campo.getName();
            Object valorCampo;

            try {
                valorCampo = campo.get(entidade);
            }catch (IllegalAccessException e){
                valorCampo = e.getMessage();
            }
            resul.append(nomeCampo).append("=").append(valorCampo).append(", ");
        }
        if (campos.length > 0){
            resul.setLength(resul.length() - 2);
        }
        resul.append("}");
        return resul.toString();
    }
}
