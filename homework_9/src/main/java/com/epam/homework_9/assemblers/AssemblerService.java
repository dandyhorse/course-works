package com.epam.homework_9.assemblers;

public class AssemblerService {

    public static EntityAssembler newEntityAssembler() {
        return new EntityAssembler();
    }

    public static ModelAssembler newModelAssembler() {
        return new ModelAssembler();
    }
}
