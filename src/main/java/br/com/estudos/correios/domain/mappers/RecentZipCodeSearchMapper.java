package br.com.estudos.correios.domain.mappers;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import br.com.estudos.correios.domain.models.RecentZipCodeSearchDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecentZipCodeSearchMapper {

    public static final RecentZipCodeSearchMapper Instance = Mappers.getMapper(RecentZipCodeSearchMapper.class);

    @Mappings({
            @Mapping(target="zipCode", source = "recentZipCodeSearchDTO.zipCode"),
            @Mapping(target="updated", source = "recentZipCodeSearchDTO.updated"),
            @Mapping(target="numberQueries", source = "recentZipCodeSearchDTO.numberQueries"),
    })
    public abstract RecentZipCodeSearch fromDTO(RecentZipCodeSearchDTO recentZipCodeSearchDTO);

    @Mappings({
            @Mapping(target="zipCode", source = "recentZipCodeSearch.zipCode"),
            @Mapping(target="updated", source = "recentZipCodeSearch.updated"),
            @Mapping(target="numberQueries", source = "recentZipCodeSearch.numberQueries"),
    })
    public abstract RecentZipCodeSearchDTO toDTO(RecentZipCodeSearch recentZipCodeSearch);

    @IterableMapping(qualifiedByName = "toDTO")
    public List<RecentZipCodeSearchDTO> map(List<RecentZipCodeSearch> children){
        List<RecentZipCodeSearchDTO> list = new ArrayList<>();
        for (RecentZipCodeSearch recentZipCodeSearch : children) {
            list.add(this.toDTO(recentZipCodeSearch));
        }

        return list;
    }

}
