package mapper;

import mapper.dto.ToysDto;
import model.Toy;

public class ToyMapper {
    public static ToysDto mapFromModel(Toy toy){
        return new ToysDto(toy.getToyId(), toy.getToyName(), toy.getToyCategory(), toy.getToy_Price(), toy.getStock_Toys());
    }
    public static Toy mapFromDto(ToysDto toy){
        return Toy.builder()
                .toyId(toy.toyId())
                .toyName(toy.toyName())
                .toyCategory(toy.toyCategory())
                .toy_Price(toy.toyPrice())
                .stock_Toys(toy.stock_Toys())
                .build();
    }
}