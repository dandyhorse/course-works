package com.epam.homework_8.dao.assemblers;

public class AssemblerService {

    public static EntityAssembler newEntityAssembler() {
        return new EntityAssembler();
    }

    public static ModelAssembler newModelAssembler() {
        return new ModelAssembler();
    }
}
