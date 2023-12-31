package py.edu.ucom.julio.service;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.julio.config.IDAO;
import py.edu.ucom.julio.entities.MetodoPago;
import py.edu.ucom.julio.repositories.MetodoPagoRepository;

@ApplicationScoped
public class MetodoPagoService implements IDAO<MetodoPago, Integer> {
    @Inject
    private MetodoPagoRepository repository;

    @Override
    public MetodoPago obtener(Integer param) {
        return this.repository.findById(param).orElse(null);
    }

    @Override
    public MetodoPago agregar(MetodoPago param) {
        return this.repository.save(param);
    }

    @Override
    public MetodoPago modificar(MetodoPago param) {
        return this.repository.save(param);
    }

    @Override
    public void eliminar(Integer param) {
        this.repository.deleteById(param);
    }

    @Override
    public List<MetodoPago> listar() {
        return this.repository.findAll();
    }

    public List<MetodoPago> buscarPorCodigo(String cod) {
        // System.out.println(cod + "CODIGO ESPERADO");
        try {
            this.repository.findByCodigo(cod);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return this.repository.findByCodigo(cod);
    }

    public Long sumIds() {
        return this.repository.sumId();
    }

}