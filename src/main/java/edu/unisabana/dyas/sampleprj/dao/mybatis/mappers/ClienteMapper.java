package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

/**
 *
 * @author cesarvefe
 */
public interface ClienteMapper {

    Cliente consultarCliente(@Param("documento") long documento);

    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    void agregarItemRentadoACliente(@Param("clienteId") int id,
                                    @Param("itemId") int idit,
                                    @Param("fechainicio") Date fechainicio,
                                    @Param("fechafin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    List<Cliente> consultarClientes();
    
}
