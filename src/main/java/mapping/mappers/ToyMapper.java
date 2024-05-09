package mapping.mappers;

import mapper.dto.ToysDto;
import mapping.dto.dto.ToyDTO;
import model.Toy;

public class ToyMapper {
    public static ToyDTO mapFromModel(Toy toy){
        return new ToyDTO(toy.getToyId(), toy.getToyName(), toy.getToyCategory(), toy.getToy_Price(), toy.getStock_Toys());
    }
    public static Toy mapFromDto(ToyDTO toy){
        return Toy.builder()
                .toyId(toy.toyId())
                .toyName(toy.toyName())
                .toyCategory(toy.toyCategory())
                .toy_Price(toy.toyPrice())
                .stock_Toys(toy.stock_Toys())
                .build();
    }
}