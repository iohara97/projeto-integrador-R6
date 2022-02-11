package com.example.demo.utils;

import com.example.demo.interfaces.ListaArmazemInterface;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MockArmazem {

    private List<ListaArmazemInterface> listaArmazemInterfaces = new ArrayList<>();
    private ListaArmazemInterface armazemInterface;
    private ListaArmazemInterface armazemInterface2;

    public MockArmazem() {

        armazemInterface = new ListaArmazemInterface() {
            @Override
            public Long getArmazem_id() {
                return 1L;
            }

            @Override
            public Integer getTotalQuantidade() {
                return 100;
            }
        };

        armazemInterface2 = new ListaArmazemInterface() {
            @Override
            public Long getArmazem_id() {
                return 3L;
            }

            @Override
            public Integer getTotalQuantidade() {
                return 200;
            }
        };

        listaArmazemInterfaces.add(armazemInterface);
        listaArmazemInterfaces.add(armazemInterface2);

    }

}
