package com.srijan.springfundamentals.util;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rashim Dhaubanjar
 */
public class ObjectMapper {

    private static ModelMapper modelMapper;
    private static ModelMapper modelMapperNonNull;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);

        modelMapperNonNull = new ModelMapper();
        modelMapperNonNull.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapperNonNull.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    private ObjectMapper() {
    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    public static <S, D> D mapExcludingNulls(final S source, D destination) {
        modelMapperNonNull.map(source, destination);
        return destination;
    }

}
