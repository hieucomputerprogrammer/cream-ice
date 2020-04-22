package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.IceCream;
import io.hieu.creamice.dto.IceCreamDTO;
import io.hieu.creamice.modelmappers.IceCreamMapper;
import io.hieu.creamice.repositories.IIceCreamRepository;
import io.hieu.creamice.services.IIceCreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IceCreamServiceImpl implements IIceCreamService {
    @Autowired
    private IIceCreamRepository IIceCreamRepository;

    @Autowired
    public IceCreamServiceImpl(IIceCreamRepository IIceCreamRepository) {
        this.IIceCreamRepository = IIceCreamRepository;
    }

    @Override
    public List<IceCreamDTO> findAll() {
        List<IceCream> iceCreams = IIceCreamRepository.findAll();
        List<IceCreamDTO> iceCreamDTOs = new ArrayList<IceCreamDTO>();
        for (IceCream iceCream : iceCreams) {
            iceCreamDTOs.add(IceCreamMapper.iceCreamToIceCreamDTO(iceCream));
        }
        return iceCreamDTOs;
    }

    @Override
    public Page<IceCreamDTO> findAll(Pageable pageable) {
        Page<IceCream> iceCreamsPage = IIceCreamRepository.findAll(pageable);
        Page<IceCreamDTO> iceCreamDTOsPage = iceCreamsPage.map(iceCream -> IceCreamMapper.iceCreamToIceCreamDTO(iceCream));
        return iceCreamDTOsPage;
    }

    @Override
    public Optional<IceCreamDTO> findById(Long id) {
        return IIceCreamRepository.findById(id)
                .map(iceCream -> IceCreamMapper.iceCreamToIceCreamDTO(iceCream));
    }

    @Override
    public IceCreamDTO create(IceCreamDTO iceCreamDTO) {
        IceCream iceCream = IceCreamMapper.iceCreamDTOToIceCream(iceCreamDTO);
        iceCream = IIceCreamRepository.save(iceCream);
        return IceCreamMapper.iceCreamToIceCreamDTO(iceCream);
    }

    @Override
    public IceCreamDTO update(IceCreamDTO iceCreamDTO, Long id) {
        Optional<IceCream> currentIceCream = IIceCreamRepository.findById(id);

        if (!currentIceCream.isPresent()) {
            throw new RuntimeException("No ice-cream ID " + id + " found!");
        }
        IceCream iceCream = currentIceCream.get();
        iceCream.setName(iceCreamDTO.getName());
        iceCream.setDescription(iceCreamDTO.getDescription());

        return IceCreamMapper.iceCreamToIceCreamDTO(iceCream);
    }

    @Override
    public void delete(Long id) {
        IIceCreamRepository.deleteById(id);
    }
}