package be.technifutur.java.timairport.model.dto;


import be.technifutur.java.timairport.model.entity.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDTO {

    private long id;
    private String name;
    private String originCountry;

    public static CompanyDTO from(Company entity) {
        if (entity == null)
            return null;

        return CompanyDTO.builder()
                .id( entity.getId() )
                .name( entity.getName() )
                .originCountry(entity.getOriginCountry() )
                .build();
    }

}
