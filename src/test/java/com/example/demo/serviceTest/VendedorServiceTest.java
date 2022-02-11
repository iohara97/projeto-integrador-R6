package com.example.demo.serviceTest;

import com.example.demo.entity.Vendedor;
import com.example.demo.repository.VendedorRepository;
import com.example.demo.service.VendedorService;
import com.example.demo.utils.MockVendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendedorServiceTest {

    private MockVendedor mockVendedor;
    private Vendedor vendedor;
    private VendedorService vendedorService;

    @BeforeEach
    public void init(){
        mockVendedor = new MockVendedor();
        vendedor = mockVendedor.getVendedor();
    }

    @Test
    public void deveVerificarVendedorSalvo(){
        VendedorRepository mock = Mockito.mock(VendedorRepository.class);
        Mockito.when(mock.save(vendedor)).thenReturn(vendedor);
        VendedorService vendedorService = new VendedorService(mock);
        Vendedor vendedorCriado = vendedorService.save(vendedor);

        assertTrue(vendedorCriado.equals(vendedor));
    }
}
