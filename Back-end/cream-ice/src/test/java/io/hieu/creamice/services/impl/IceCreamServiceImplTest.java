package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.IceCream;
import io.hieu.creamice.dto.IceCreamDTO;
import io.hieu.creamice.repositories.IIceCreamRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IceCreamServiceImplTest {
    @Test
    public void find_findAll_iceCreamsNotFound() {
        IIceCreamRepository iceCreamRepository = mock(IIceCreamRepository.class);

        when(iceCreamRepository.findAll()).thenReturn(Collections.emptyList());
        IceCreamServiceImpl iceCreamServiceImpl = new IceCreamServiceImpl(iceCreamRepository);

        List<IceCreamDTO> iceCreams = iceCreamServiceImpl.findAll();
        assertTrue(iceCreams.isEmpty());
    }


    @Test
    public void find_findAll_iceCreamsFound() {
        IIceCreamRepository iceCreamRepository = mock(IIceCreamRepository.class);
        List<IceCream> iceCreams = new ArrayList<>();
        iceCreams.add(new IceCream());


        when(iceCreamRepository.findAll()).thenReturn(iceCreams);
        IceCreamServiceImpl iceCreamServiceImpl = new IceCreamServiceImpl(iceCreamRepository);

        List<IceCreamDTO> iceCreamsList = iceCreamServiceImpl.findAll();
        assertFalse(iceCreamsList.isEmpty());
    }

    @Test
    public void find_findIceCreamById_iceCreamFound() {
        final Long mockId = 1L;
        IIceCreamRepository iceCreamRepository = mock(IIceCreamRepository.class);
        IceCream iceCream = new IceCream();

        when(iceCreamRepository.findById(mockId)).thenReturn(Optional.of(iceCream));
        IceCreamServiceImpl iceCreamServiceImpl = new IceCreamServiceImpl(iceCreamRepository);

        Optional<IceCreamDTO> iceCreamDTO = iceCreamServiceImpl.findById(mockId);
        assertTrue(iceCreamDTO.isPresent());
    }

    @Test
    public void find_findIceCreamById_iceCreamNotFound() {
        final Long mockId = 1L;
        IIceCreamRepository iceCreamRepository = mock(IIceCreamRepository.class);

        when(iceCreamRepository.findById(mockId)).thenReturn(Optional.empty());
        IceCreamServiceImpl iceCreamServiceImpl = new IceCreamServiceImpl(iceCreamRepository);

        Optional<IceCreamDTO> iceCreamDTO = iceCreamServiceImpl.findById(mockId);
        assertTrue(iceCreamDTO.isPresent());
    }
}