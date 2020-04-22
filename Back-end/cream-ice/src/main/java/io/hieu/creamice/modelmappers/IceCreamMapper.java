package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.IceCream;
import io.hieu.creamice.dto.IceCreamDTO;

public class IceCreamMapper {
    public static IceCreamDTO iceCreamToIceCreamDTO(IceCream iceCream) {
        IceCreamDTO iceCreamDTO;
        iceCreamDTO = new IceCreamDTO(iceCream.getId(), iceCream.getName(), iceCream.getDescription());
        return iceCreamDTO;
    }

    public static IceCream iceCreamDTOToIceCream(IceCreamDTO iceCreamDTO) {
        IceCream iceCream = new IceCream();
        iceCream.setName(iceCreamDTO.getName());
        iceCream.setDescription(iceCreamDTO.getDescription());
        return iceCream;
    }
}