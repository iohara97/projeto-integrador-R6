package com.example.demo.serviceTest;

import com.example.demo.dto.ArmazemDTO;
import com.example.demo.interfaces.ListaArmazemInterface;
import com.example.demo.repository.EstoqueRepository;
import com.example.demo.service.ArmazemService;
import com.example.demo.utils.MockArmazem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmazemServiceTest {

    private MockArmazem mockArmazem;
    private List<ListaArmazemInterface> listaArmazemInterfaces = new ArrayList<>();
    private ListaArmazemInterface armazemInterface;
    private ListaArmazemInterface armazemInterface2;

    @BeforeEach
    public void init() {
        mockArmazem = new MockArmazem();
        listaArmazemInterfaces = mockArmazem.getListaArmazemInterfaces();
        armazemInterface = mockArmazem.getArmazemInterface();
        armazemInterface2 = mockArmazem.getArmazemInterface2();
    }

    @Test
    public void deveVerificarEstoquePorArmazem() {

        EstoqueRepository mock = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mock.getEstoqueByArmazem(1L)).thenReturn(listaArmazemInterfaces);
        ArmazemService armazemService = new ArmazemService(mock);
        ArmazemDTO armazemDTO = armazemService.getEstoqueByArmazem(1L);

        assertEquals(armazemDTO.getListaArmazem().size(),2);

    }

}
