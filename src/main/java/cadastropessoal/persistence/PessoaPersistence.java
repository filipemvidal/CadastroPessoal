package cadastropessoal.persistence;

import cadastropessoal.model.Pessoa;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author filip
 */
public class PessoaPersistence {

    private static final String PATH = "data" + File.separator + "pessoas.json";
    
    public void save(List<Pessoa> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        
        File directory = new File("data");
        if(!directory.exists())
            directory.mkdirs();
        
        Archive.save(PATH, json);
    }

    public List<Pessoa> read() {
        Gson gson = new Gson();
        
        String json = Archive.read(PATH);
        
        List<Pessoa> funcionarios = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Pessoa>>() {}.getType();
        
            funcionarios = gson.fromJson(json, tipoLista);
        
            if (funcionarios == null)
                funcionarios = new ArrayList<>();
        }

        return funcionarios;
}
    
}
