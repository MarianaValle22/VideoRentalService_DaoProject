package edu.unisabana.dyas.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Cliente;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Mariana Valle
 */

public class MyBatisExample {
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                System.err.println("Error al cargar mybatis-config.xml: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejemplo de uso de MyBATIS
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) {
        try (SqlSession sqlss = getSqlSessionFactory().openSession()) {
            ClienteMapper clienteMapper = sqlss.getMapper(ClienteMapper.class);
            ItemMapper itemMapper = sqlss.getMapper(ItemMapper.class);

            // Agregar un ítem rentado a un cliente
            agregarItemRentadoACliente(sqlss, clienteMapper, 987654321, 789, "2024-03-01", "2024-03-10");
            sqlss.commit();

            // Consultar un cliente específico
            consultarClientePorId(clienteMapper, 987654321);

            // Consultar todos los clientes
            consultarTodosLosClientes(clienteMapper);

            // Confirmar transacciones
            sqlss.commit();

        } catch (PersistenceException e) {
            System.err.println("Error de MyBatis: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }

    private static void consultarClientePorId(ClienteMapper clienteMapper, int clienteId) {
        System.out.println("\n🔎 Depuración MyBatis - Consultando cliente con ID: " + clienteId);

        Cliente cliente = clienteMapper.consultarCliente(clienteId);

        if (cliente != null) {
            System.out.println("✔ Cliente encontrado: " + cliente);
        } else {
            System.out.println("❌ ERROR: MyBatis no pudo recuperar el cliente con ID: " + clienteId);
            System.out.println("🔎 Verificando directamente en la base de datos...");

            try (SqlSession sqlSession = MyBatisExample.getSqlSessionFactory().openSession()) {
                List<Cliente> clientes = sqlSession.selectList("edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper.consultarClientes");
                for (Cliente c : clientes) {
                    System.out.println("➡ Cliente en la base de datos: Documento=" + c.getDocumento() + ", Nombre=" + c.getNombre());
                }
            }
        }
    }




    private static void consultarTodosLosClientes(ClienteMapper clienteMapper) {
        System.out.println("\nLista de todos los clientes:");
        List<Cliente> clientes = clienteMapper.consultarClientes();
        for (Cliente c : clientes) {
            System.out.println("Cliente recuperado desde MyBatis: Documento=" + c.getDocumento() + ", Nombre=" + c.getNombre());
        }
    }

    private static void agregarItemRentadoACliente(SqlSession sqlss, ClienteMapper clienteMapper, int clienteId, int itemId, String fechaInicio, String fechaFin) {
        try {
            clienteMapper.agregarItemRentadoACliente(clienteId, itemId, Date.valueOf(fechaInicio), Date.valueOf(fechaFin));
            sqlss.commit();  // Confirmar la transacción
            System.out.println("\nÍtem rentado agregado correctamente para el cliente con ID: " + clienteId);
        } catch (Exception e) {
            System.err.println("Error al agregar ítem rentado: " + e.getMessage());
        }
    }
}
