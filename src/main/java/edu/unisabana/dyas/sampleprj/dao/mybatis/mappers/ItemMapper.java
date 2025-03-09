package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Item;

/**
 *
 * @author cesarvefe
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(int id);
    
    public void insertarItem(@Param("item") Item it);

    public void agregarItemRentadoACliente(
            @Param("clienteId") int clienteId,
            @Param("itemId") int itemId,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin
    );
        
}
